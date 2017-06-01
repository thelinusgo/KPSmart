package views.gui;

import java.awt.Color;
import java.awt.Dimension;

import rabe_studios.mvc.views.View;

/**
 * All the GUI implementation will be here
 * 
 * @author Chris
 *
 */
@SuppressWarnings("serial")
public abstract class ViewGUI extends View{

	public ViewGUI() {
		super(new Dimension(800,600), new Color(242, 230, 255));
	}

}
