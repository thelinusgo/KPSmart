package server.controllers;

import java.util.HashMap;
import java.util.Map;

import rabe_studios.components.resources.BaseResource;
import rabe_studios.mvc.controllers.BaseAppController;
import rabe_studios.mvc.controllers.IViewController;

/**
 * 
 * 
 * @author Chris
 */
public class PanelController extends BaseAppController{

	public PanelController(BaseResource resource) {
		super("home", resource);
	}

	@Override
	protected Map<String, IViewController> createControllers() {
		Map<String, IViewController> tmp = new HashMap<String, IViewController>();
		tmp.put("home", new HomeController(resource));
		return tmp;
	}

}
