package server.handlers.operations;

import javax.swing.JTextArea;

public abstract class OperationHandler {
	// If GUI is implemented
	protected JTextArea logPanel = null;

	public void setLogPanel(JTextArea logPanel) {
		this.logPanel = logPanel;
	}

	protected void printLog(String message) {
		if (logPanel == null) {
			System.out.println(message);
		} else {
			logPanel.append(message);
		}
	}
}
