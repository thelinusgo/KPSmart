package server.model.manager;

import org.eclipse.jetty.server.Server;

import server.model.initialisers.ConsoleDBInitialiser;
import server.model.initialisers.DatabaseInitialiser;
import server.model.thread.ServerThread;

public class ServerManager {

	private int port = 8080;
	private DatabaseInitialiser dbInitialiser;
	private Thread outerThread;
	private ServerThread serverThread;

	public ServerManager() {
		this.dbInitialiser = new ConsoleDBInitialiser();
	}

	public void start(String port) {
		dbInitialiser.initialiseDatabase();
		if (outerThread != null || serverThread != null) {
			System.out.println("Stopping Previous Server...");
			destroyThreads(outerThread, serverThread);
		}
		// Start Server
		System.out.println("Starting New Server...");
		if (port == null || !port.matches("[-+]?\\d*\\.?\\d+"))
			return;
		this.port = Integer.parseInt(port);
		Server server = new Server(this.port);
		this.serverThread = new ServerThread(server);
		this.outerThread = new Thread(serverThread);
		this.outerThread.start();
	}

	public void stop() {
		if (outerThread != null || serverThread != null) {
			System.out.println("Stopping Server...");
			destroyThreads(outerThread, serverThread);
		} else {
			System.out.println("Server is offline");
		}
	}

	private void destroyThreads(Thread outerThread, ServerThread serverThread) {
		if (serverThread != null) {
			serverThread.stop(); // stop server internally
			this.serverThread = null; // leave old instance to garbage collector
		}
		if (outerThread != null) {
			// leave old outerThread to garbage collector
			this.outerThread = null;
		}
	}
}
