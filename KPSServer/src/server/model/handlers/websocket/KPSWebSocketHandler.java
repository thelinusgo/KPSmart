package server.model.handlers.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import server.model.handlers.operations.Handlers;

/**
 * Handles all the web socket operations. This class supports the following operations:
 * 
 * - close
 * - error
 * - message
 * - connect
 * 
 * @author Chris
 *
 */
@WebSocket
public class KPSWebSocketHandler {

	@OnWebSocketClose
	public void onClose(int statusCode, String reason) {
		Handlers.getCloseHandler().handle(statusCode, reason);
	}

	@OnWebSocketError
	public void onError(Throwable t) {
		Handlers.getErrorHandler().handle(t);
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {
		Handlers.getConnectHandler().handle(session);
	}

	@OnWebSocketMessage
	public void onMessage(String message) {
		Handlers.getMessageHandler().handle(message);
	}
}
