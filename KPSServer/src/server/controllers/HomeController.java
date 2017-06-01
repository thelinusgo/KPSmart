package server.controllers;

import rabe_studios.components.resources.BaseResource;
import rabe_studios.mvc.controllers.BaseViewController;
import rabe_studios.mvc.views.View;
import views.pages.HomeView;

public class HomeController extends BaseViewController{

	public HomeController(BaseResource resource) {
		super(resource);
	}

	@Override
	protected View createView() {
		HomeView homeView = new HomeView();
		// I usually add listeners here
		return homeView;
	}

}
