package server.model.manager;

import org.eclipse.jetty.server.Server;

import server.model.initialisers.ConsoleDBInitialiser;
import server.model.initialisers.DatabaseInitialiser;
import server.model.thread.ServerThread;

public class ServerManager {

	private int port = 8080;
	private DatabaseInitialiser dbInitialiser;
	private ServerThread serverThread;

	public ServerManager() {
		this.dbInitialiser = new ConsoleDBInitialiser();
	}

	public void start(String port) {
		dbInitialiser.initialiseDatabase();
		if (serverThread != null) {
			System.out.println("Stopping Previous Server...");
			destroyThread(serverThread);
		}
		// Start Server
		System.out.println("Starting New Server...");
		if (port == null || !port.matches("[-+]?\\d*\\.?\\d+"))
			return;
		this.port = Integer.parseInt(port);
		Server server = new Server(this.port);
		this.serverThread = new ServerThread(server);
		Thread outerThread = new Thread(serverThread);
		outerThread.start();
	}

	public void stop() {
		if (serverThread != null) {
			System.out.println("Stopping Server...");
			destroyThread(serverThread);
		} else {
			System.out.println("Server is offline");
		}
	}

	private void destroyThread(ServerThread serverThread) {
		if (serverThread != null) {
			serverThread.stop(); // stop server internally
			this.serverThread = null; // leave old instance to garbage collector
		}
	}
}
