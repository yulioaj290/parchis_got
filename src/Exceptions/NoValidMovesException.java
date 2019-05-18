/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exceptions;

/**
 *
 * @author yulio
 */
public class NoValidMovesException extends Exception{

    public NoValidMovesException() {
        super("You can't do any movement.");
    }

}
