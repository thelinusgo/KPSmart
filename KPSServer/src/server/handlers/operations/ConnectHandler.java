package server.handlers.operations;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;

/**
 * Handles all the connect operations of the web socket class
 * 
 * @author Chris
 */
public class ConnectHandler extends OperationHandler {

	public void handle(Session session) {
		String message = "Connect: " + session.getRemoteAddress().getAddress();
		printLog(message);
		try {
			session.getRemote().sendString("Welcome to KPSSmart");
		} catch (IOException e) {
			printLog(e.getMessage());
		}
	}
}
