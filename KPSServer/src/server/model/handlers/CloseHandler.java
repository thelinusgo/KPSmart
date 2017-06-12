package server.model.handlers;

/**
 * The handle method should be called when for the web socket close operation.
 * This class prints messages to the console.
 * 
 * @author Chris
 *
 */
public class CloseHandler {

	public void handle(int statusCode, String reason) {
		String message = "Close: statusCode=" + statusCode + ", reason=" + reason;
		System.out.println(message);
	}
}
