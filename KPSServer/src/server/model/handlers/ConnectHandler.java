package server.model.handlers;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;

/**
 * Handles client connections from the web socket. Prints out new connections in
 * the console.
 * 
 * @author Chris
 *
 */
public class ConnectHandler {

	public void handle(Session session) {
		String message = "Connect: " + session.getRemoteAddress().getAddress();
		System.out.println(message);
		try {
			session.getRemote().sendString("Welcome to KPSmart");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
