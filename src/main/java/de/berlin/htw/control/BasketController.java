package de.berlin.htw.control;

import javax.enterprise.context.Dependent;
import javax.ws.rs.NotSupportedException;

import de.berlin.htw.boundary.dto.Basket;

/**
 * @author Alexander Stanik [stanik@htw-berlin.de]
 */
@Dependent
public class BasketController {

    public Basket todo() {
    	throw new NotSupportedException("TODO");
    }
}
