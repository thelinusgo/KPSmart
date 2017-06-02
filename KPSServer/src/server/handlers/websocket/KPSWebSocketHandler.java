package server.handlers.websocket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import server.handlers.operations.Operations;

/**
 * This class is the web socket class. It contains four operations:
 * 
 * - close
 * - error
 * - connect
 * - message
 * 
 * @author Chris
 */
@WebSocket
public class KPSWebSocketHandler {

	@OnWebSocketClose
	public void onClose(int statusCode, String reason){
		Operations.getCloseHandler().handle(statusCode, reason);
	}
	
	@OnWebSocketError
	public void onError(Throwable t){
		Operations.getErrorHandler().handle(t);
	}
	
	@OnWebSocketConnect
	public void onConnect(Session session){
		Operations.getConnectHandler().handle(session);
	}
	
	@OnWebSocketMessage
	public void onMessage(String message){
		Operations.getMessageHandler().handle(message);
	}
}
