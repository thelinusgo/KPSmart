package server.model.handlers;

/**
 * Handles all the errors of the web socket.
 * 
 * @author Chris
 *
 */
public class ErrorHandler {

	public void handle(Throwable t) {
		System.out.println("Error: " + t.getMessage());
	}
}
