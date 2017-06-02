package server.handlers.operations;

import javax.swing.JTextArea;

/**
 * This class contains the handlers for the web socket operations.
 * 
 * @author Chris
 *
 */
public class Operations {

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

	public static void setLogPanel(JTextArea logPanel) {
		messageHandler.setLogPanel(logPanel);
		errorHandler.setLogPanel(logPanel);
		closeHandler.setLogPanel(logPanel);
		connectHandler.setLogPanel(logPanel);
	}
}
