package server.model.thread;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import server.model.handlers.websocket.KPSWebSocketHandler;

/**
 * This class starts the server and should be ran in a separate thread.
 * 
 * @author Chris
 *
 */
public class ServerThread implements Runnable {

	private Server server;

	public ServerThread(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		WebSocketHandler wsHandler = new WebSocketHandler() {
			public void configure(WebSocketServletFactory factory) {
				factory.register(KPSWebSocketHandler.class);
			}
		};
		server.setHandler(wsHandler);
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void stop() {
		try {
			server.stop();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
