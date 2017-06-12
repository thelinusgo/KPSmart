package server.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This class contains the graphical components of the user interface. No action
 * listeners are implemented. Can be reused.
 * 
 * @author Chris
 */
@SuppressWarnings("serial")
public class ServerPanel extends JPanel {

	protected JButton startButton = new JButton("Start");
	protected JTextArea portField = new JTextArea(1, 4);
	private JLabel portLabel = new JLabel("Port Number:");

	public ServerPanel() {
		this.initialiseComponents();
	}

	protected void initialiseComponents() {
		this.setLayout(new GridLayout(2, 0));
		// Set up components
		Font font = new Font("Arial", Font.PLAIN, 20);
		portField.setForeground(Color.WHITE);
		portLabel.setForeground(Color.WHITE);
		portField.setFont(font);
		portLabel.setFont(font);
		// Create containers
		JPanel startPanel = new JPanel();
		JPanel portFldPanel = new JPanel(); // port field panel
		JPanel portLblPanel = new JPanel(); // port label panel
		JPanel portInfo = new JPanel();
		// Set up containers
		portFldPanel.setOpaque(false);
		portLblPanel.setOpaque(false);
		portInfo.setBackground(new Color(40, 40, 40));
		portInfo.setLayout(new GridLayout(0, 2));
		// Add components into containers
		startPanel.add(startButton);
		portFldPanel.add(portField);
		portLblPanel.add(portLabel);
		portInfo.add(portLblPanel);
		portInfo.add(portFldPanel);
		// Add panels to main panel
		this.add(startPanel);
		this.add(portInfo);
		// Set up panel attributes
		this.setPreferredSize(new Dimension(450, 100));
	}
}