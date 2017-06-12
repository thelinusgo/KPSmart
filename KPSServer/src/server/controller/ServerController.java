package server.controller;

import server.views.ServerPanel;

/**
 * Application adds action listeners to the components in the server panel.
 * 
 * @author Chris
 */
@SuppressWarnings("serial")
public class ServerController extends ServerPanel {

	public ServerController() {
		addActionListeners();
	}

	private void addActionListeners() {
		startButton.addActionListener(e -> {
			// TODO create server and start it
		});
	}
}
