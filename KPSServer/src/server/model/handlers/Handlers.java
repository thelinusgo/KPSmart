package server.model.handlers;

/**
 * This class is a singleton of all the handlers for the web socket.
 * 
 * @author Chris
 *
 */
public class Handlers {

	private static ConnectHandler connectHandler = new ConnectHandler();
	private static CloseHandler closeHandler = new CloseHandler();
	private static MessageHandler messageHandler = new MessageHandler();
	private static ErrorHandler errorHandler = new ErrorHandler();

	public static ConnectHandler getConnectHandler() {
		return connectHandler;
	}

	public static CloseHandler getCloseHandler() {
		return closeHandler;
	}

	public static ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	public static MessageHandler getMessageHandler() {
		return messageHandler;
	}
}
