package de.berlin.htw.boundary;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

import de.berlin.htw.boundary.dto.Basket;
import de.berlin.htw.boundary.dto.Item;
import de.berlin.htw.control.BasketController;

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
    public Basket getBasket() {
    	logger.info(context.getUserPrincipal().getName() 
    			+ " is calling " + uri.getAbsolutePath());
    	
        return basket.todo();
    }

    @DELETE
    public void clearBasket() {
    	logger.info(context.getUserPrincipal().getName() 
    			+ " is calling " + uri.getAbsolutePath());
    	// no content
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkout() {
    	logger.info(context.getUserPrincipal().getName() 
    			+ " is calling " + uri.getAbsolutePath());
    	// return the url of orders and the created order itself
        return Response
        		.created(uri.getBaseUriBuilder().path("hierFehltNoEtwas").build())
        		.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItem(final Item item) {
    	logger.info(context.getUserPrincipal().getName() 
    			+ " is calling " + uri.getAbsolutePath());
    	// return basket with remaining balance
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeItem() {
    	logger.info(context.getUserPrincipal().getName() 
    			+ " is calling " + uri.getAbsolutePath());
    	// return basket with remaining balance
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }

    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeCount(final Item item) {
    	logger.info(context.getUserPrincipal().getName() 
    			+ " is calling " + uri.getAbsolutePath());
    	// return basket with remaining balance
        return Response.status(Status.NOT_IMPLEMENTED).build();
    }

}