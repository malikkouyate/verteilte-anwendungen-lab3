package de.berlin.htw.control;

import javax.enterprise.context.Dependent;
import javax.ws.rs.NotSupportedException;

/**
 * @author Alexander Stanik [stanik@htw-berlin.de]
 */
@Dependent
public class BasketController {

    public void todo() {
    	throw new NotSupportedException("TODO");
    }
}
