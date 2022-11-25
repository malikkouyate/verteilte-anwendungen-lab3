package de.berlin.htw.control;

import javax.enterprise.context.Dependent;
import javax.ws.rs.NotSupportedException;

import de.berlin.htw.boundary.dto.Orders;

/**
 * @author Alexander Stanik [stanik@htw-berlin.de]
 */
@Dependent
public class OrderController {

    public Orders todo() {
    	throw new NotSupportedException("TODO");
    }

}
