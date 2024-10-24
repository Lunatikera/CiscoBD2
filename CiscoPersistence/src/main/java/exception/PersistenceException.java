/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package exception;

/**
 *
 * @author carli
 */
public class PersistenceException extends Exception {

    /**
     * Creates a new instance of <code>PersistenceException</code> without
     * detail message.
     */
    public PersistenceException() {
    }

    /**
     * Constructs an instance of <code>PersistenceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PersistenceException(String msg) {
        super(msg);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
