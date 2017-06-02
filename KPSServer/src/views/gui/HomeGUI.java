package views.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import rabe_studios.components.panels.AlignedPanel;
import rabe_studios.components.panels.CenteredPanel;
import rabe_studios.components.panels.VerticalPanel;
import rabe_studios.components.panels.WrapperPanel;
import views.components.Button;
import views.components.Label;
import views.components.TextField;

/**
 * Contains port text box, start server button and server logs
 * 
 * @author Chris
 *
 */
@SuppressWarnings("serial")
public abstract class HomeGUI extends ViewGUI{
	
	protected JTextField portField = new TextField(90, 50, "8081", 30);
	protected JButton startButton = new Button("Start", 100, 100);
	protected JTextArea logs = new JTextArea(50,40);
	
	@Override
	protected void initialiseComponents() {
		this.setLayout(new BorderLayout());
		// Set up log and port field
		JPanel portLabel = new AlignedPanel(FlowLayout.LEFT, 100, 60, new Label("Port:", 20));
		JPanel portPanel = new AlignedPanel(FlowLayout.LEFT, 100, 60, portField);
		JPanel startLabel = new AlignedPanel(FlowLayout.LEFT, 100, 60, new Label("Start:", 20));
		JPanel portInfo = new VerticalPanel(150, 280,new WrapperPanel(portLabel), new WrapperPanel(portPanel), new WrapperPanel(startLabel), startButton);
		JScrollPane scroll = new JScrollPane (logs);
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    scroll.setPreferredSize(new Dimension(500,500));
		this.add(new WrapperPanel(new CenteredPanel(new Label("Server Admin Panel", 20))), BorderLayout.NORTH);
		this.add(new WrapperPanel(scroll), BorderLayout.CENTER);
		this.add(new WrapperPanel(portInfo), BorderLayout.WEST);
	}

}
