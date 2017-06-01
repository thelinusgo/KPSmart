package main;

import rabe_studios.components.resources.BaseResource;
import server.controllers.PanelController;
import util.resources.Resource;
import views.window.PanelWindow;

public class KPSServer {

	public static void main(String[] args){
		// Initialise the resource class
		BaseResource resource = new Resource();
		PanelController controller = new PanelController(resource);
		controller.startApplication(new PanelWindow(resource));
	}
}
