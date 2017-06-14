package server.model.handlers.operations;

/**
 * This class is a singleton of all the handlers for the web socket.
 * 
 * @author Chris
 *
 */
public class Handlers {

	private ConnectHandler connectHandler;
	private CloseHandler closeHandler;
	private MessageHandler messageHandler;
	private ErrorHandler errorHandler;

	public Handlers() {
		connectHandler = new ConnectHandler();
		closeHandler = new CloseHandler();
		messageHandler = new MessageHandler();
		errorHandler = new ErrorHandler();
	}

	public ConnectHandler getConnectHandler() {
		return connectHandler;
	}

	public CloseHandler getCloseHandler() {
		return closeHandler;
	}

	public ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	public MessageHandler getMessageHandler() {
		return messageHandler;
	}
}
