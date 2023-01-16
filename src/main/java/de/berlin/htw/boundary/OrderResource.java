package de.berlin.htw.boundary;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

import de.berlin.htw.boundary.dto.Orders;
import de.berlin.htw.control.OrderController;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

/**
 * @author Alexander Stanik [stanik@htw-berlin.de]
 */
@Path("/orders")
public class OrderResource {

    @Context
    UriInfo uri;

    @Context
    SecurityContext context;

    @Inject
    OrderController order;

    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieve all completed orders of a users.")
    @APIResponse(responseCode = "200", description = "Retieve all completed orders successfully")
    @APIResponse(responseCode = "401", description = "No or wrong User Id provided as header")
    public int getCompletedOrders() {
    	logger.info(context.getUserPrincipal().getName()
    			+ " is calling " + uri.getAbsolutePath());

        return order.todo();
    }

}
