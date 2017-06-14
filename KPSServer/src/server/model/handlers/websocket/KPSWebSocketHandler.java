package server.model.handlers.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import server.model.handlers.operations.Handlers;

/**
 * Handles all the web socket operations. This class supports the following
 * operations:
 * 
 * - close - error - message - connect
 * 
 * @author Chris
 *
 */
@WebSocket
public class KPSWebSocketHandler {

	private Handlers handlers = new Handlers();

	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		handlers.getCloseHandler().handle(statusCode, reason);
	}

	@OnWebSocketError
	public void onError(Throwable t) {
		handlers.getErrorHandler().handle(t);
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {
		handlers.getConnectHandler().handle(session);
	}

	@OnWebSocketMessage
	public void onMessage(String message) {
		handlers.getMessageHandler().handle(message);
	}
}
