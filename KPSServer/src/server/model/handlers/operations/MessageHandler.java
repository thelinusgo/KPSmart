package server.model.handlers.operations;

/**
 * Handles all the messages received by the web socket
 * 
 * @author Chris
 *
 */
public class MessageHandler {

	public void handle(String message) {
		System.out.println(message);
	}
}
