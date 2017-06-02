package server.handlers.operations;

/**
 * Handles all the errors of the web socket
 * 
 * @author Chris
 *
 */
public class ErrorHandler extends OperationHandler {

	public void handle(Throwable t) {
		String message = "Error: " + t.getMessage();
		printLog(message);
	}
}
