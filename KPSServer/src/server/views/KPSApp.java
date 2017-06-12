package server.views;

import javax.swing.JFrame;

import server.controller.ServerController;

@SuppressWarnings("serial")
public class KPSApp extends JFrame {

	public KPSApp() {
		super("KPS Server Panel");
		this.add(new ServerController());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
