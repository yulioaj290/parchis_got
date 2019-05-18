/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exceptions;

/**
 *
 * @author yulio
 */
public class ExHousesSames extends Exception {

    /**
     * Creates a new instance of <code>FacsIguales</code> without detail message.
     */
    public ExHousesSames() {
        super("¡You can't shoose same Houses!");
    }


    /**
     * Constructs an instance of <code>FacsIguales</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ExHousesSames(String msg) {
        super(msg);
    }
}
