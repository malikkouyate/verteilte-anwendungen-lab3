package de.berlin.htw.boundary;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import de.berlin.htw.entity.dto.UserEntity;
import io.vertx.core.json.Json;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.redis.client.Redis;
import io.vertx.redis.client.impl.RedisClient;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import de.berlin.htw.boundary.dto.Basket;
import de.berlin.htw.boundary.dto.Item;
import de.berlin.htw.boundary.dto.Order;
import de.berlin.htw.control.BasketController;

import java.util.Map;

/**
 * @author Alexander Stanik [stanik@htw-berlin.de]
 */
@Path("/basket")
public class BasketResource {

    @Context
    UriInfo uri;

    @Context
    SecurityContext context;

    @Inject
    BasketController basket;


    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieve the basket with all items.")
    @APIResponse(responseCode = "200", description = "Retieve all items in basket successfully")
    @APIResponse(responseCode = "401", description = "No or wrong User Id provided as header")
    public Map<String, Item> getBasket() {
    	logger.info(context.getUserPrincipal().getName()
    			+ " is calling " + uri.getAbsolutePath());
        int userId = 1;
        return basket.todo(userId);
    }

    @DELETE
    @Operation(summary = "Remove all items from basket.")
    @APIResponse(responseCode = "204", description = "Items removed successfully")
    @APIResponse(responseCode = "401", description = "No or wrong User Id provided as header")
    public void clearBasket() {
    	logger.info(context.getUserPrincipal().getName()
    			+ " is calling " + uri.getAbsolutePath());
        basket.deleteAll(1);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Checkout the basket and complete the order.")
    @APIResponse(responseCode = "201", description = "Checkout successfully",
            headers = @Header(name = "Location", description = "URL to retrive all orders"),
            content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Order.class)) )
    @APIResponse(responseCode = "401", description = "No or wrong User Id provided as header")
    @APIResponse(responseCode = "404", description = "No product with this ID in the basket")
    public Response checkout() {
    	logger.info(context.getUserPrincipal().getName()
    			+ " is calling " + uri.getAbsolutePath());
    	// return the url of orders and the created order itself
        return Response
        		.created(uri.getBaseUriBuilder().path("hierFehltNoEtwas").build())
        		.build();
    }

    @POST
    @Path("{productId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add an item to basket.")
    @APIResponse(responseCode = "201", description = "Item added successfully",
        content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Basket.class)) )
    @APIResponse(responseCode = "400", description = "Invalid request message")
    @APIResponse(responseCode = "401", description = "No or wrong User Id provided as header")
    @APIResponse(responseCode = "409", description = "Another product with this ID already exist in the basket")
    public Float addItem(
            @Parameter(description = "ID of the product", required = true) @PathParam("productId") final String productId,
            @Parameter(description = "The item to add in the basket", required = true) final Item item) {
    	logger.info(context.getUserPrincipal().getName()
    			+ " is calling " + uri.getAbsolutePath());
        return basket.add(1, productId, item);
    }

    @DELETE
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Remove an item from basket.")
    @APIResponse(responseCode = "200", description = "Item removed successfully",
        content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Basket.class)) )
    @APIResponse(responseCode = "401", description = "No or wrong User Id provided as header")
    @APIResponse(responseCode = "404", description = "No product with this ID in the basket")
    public float removeItem(
            @Parameter(description = "ID of the product", required = true) @PathParam("productId") final String productId
            ) {
    	logger.info(context.getUserPrincipal().getName()
    			+ " is calling " + uri.getAbsolutePath());


    	// return basket with remaining balance
        return basket.deleteItem(1, productId);
    }

    @PATCH
    @Path("{productId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Change the number of an item in the basket.")
    @APIResponse(responseCode = "200", description = "Number changed successfully",
        content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Basket.class)) )
    @APIResponse(responseCode = "400", description = "Invalid request message")
    @APIResponse(responseCode = "401", description = "No or wrong User Id provided as header")
    @APIResponse(responseCode = "404", description = "No product with this ID in the basket")
    public Float changeCount(
            @Parameter(description = "ID of the product", required = true) @PathParam("productId") final String productId,
            @Parameter(description = "The number of that product in the basket", required = true) final Item item) {
    	logger.info(context.getUserPrincipal().getName()
    			+ " is calling " + uri.getAbsolutePath());
    	// return basket with remaining balance
        return basket.changeCount(1,productId, item);
    }

}
