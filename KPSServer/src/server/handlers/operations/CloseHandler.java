package server.handlers.operations;

/**
 * Handles the close operation of the web socket
 * 
 * @author Chris
 *
 */
public class CloseHandler extends OperationHandler {

	public void handle(int statusCode, String reason) {
		String message = "Close: statusCode=" + statusCode + ", reason=" + reason;
		printLog(message);
	}
}
