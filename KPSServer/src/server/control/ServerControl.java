package server.control;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import server.handlers.websocket.KPSWebSocketHandler;

public class ServerControl {

	private int port = 8080;
	private Server server;

	public void start(String port) throws Exception{
		if (port == null || !port.matches("[-+]?\\d*\\.?\\d+")) {
			return;
		}
		this.port = Integer.parseInt(port);
		this.server = new Server(this.port);
		
		WebSocketHandler wsHandler = new WebSocketHandler(){
			@Override
			public void configure(WebSocketServletFactory factory){
				factory.register(KPSWebSocketHandler.class);
			}
		};
		
		server.setHandler(wsHandler);
		server.start();
		server.join();
	}
}
