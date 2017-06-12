package server.controller;

import server.model.manager.ServerManager;
import server.views.ServerPanel;

/**
 * Application adds action listeners to the components in the server panel.
 * 
 * @author Chris
 */
@SuppressWarnings("serial")
public class ServerController extends ServerPanel {

	private ServerManager manager;

	public ServerController() {
		manager = new ServerManager();
		addActionListeners();
	}

	private void addActionListeners() {
		startButton.addActionListener(e -> {
			manager.start(portField.getText());
		});
		stopButton.addActionListener(e -> {
			manager.stop();
		});
	}
}
