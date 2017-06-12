package server.model.manager;

import org.eclipse.jetty.server.Server;

import server.model.initialisers.ConsoleDBInitialiser;
import server.model.initialisers.DatabaseInitialiser;
import server.model.thread.ServerThread;

public class ServerManager {

	private int port = 8080;
	private DatabaseInitialiser dbInitialiser;

	public ServerManager() {
		this.dbInitialiser = new ConsoleDBInitialiser();
	}

	public void start(String port) {
		dbInitialiser.initialiseDatabase();
		// Start Server
		System.out.println("Starting Server...");
		if (port == null || !port.matches("[-+]?\\d*\\.?\\d+"))
			return;
		this.port = Integer.parseInt(port);
		Server server = new Server(this.port);
		Thread serverThread = new Thread(new ServerThread(server));
		serverThread.start();
	}
}
