package views.window;

import java.awt.Color;

import rabe_studios.components.resources.BaseResource;
import rabe_studios.mvc.views.Window;

@SuppressWarnings("serial")
public class PanelWindow extends Window {

	public PanelWindow(BaseResource resource) {
		super("KPS Server Admin Panel", 800, 600, new Color(242, 230, 255), true, null, resource);
	}
}
