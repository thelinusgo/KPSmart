package server.handlers.operations;

/**
 * Handles all the messages received by the web socket
 * 
 * @author Chris
 *
 */
public class MessageHandler extends OperationHandler {

	public void handle(String message) {
		printLog(message);
	}
}
