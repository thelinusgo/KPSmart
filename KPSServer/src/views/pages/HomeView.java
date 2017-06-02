package views.pages;

import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import views.gui.HomeGUI;

@SuppressWarnings("serial")
public class HomeView extends HomeGUI {

	public HomeView() {
		this.initialiseComponents();
	}

	// Add Action Listeners

	public void addStartButtonListener(ActionListener listener) {
		startButton.addActionListener(listener);
	}
	
	// Getters
	
	public JTextArea getLogPanel(){
		return logs;
	}

	public String getPort() {
		return portField.getText();
	}
}
