package views.controllers;

import rabe_studios.components.resources.BaseResource;
import rabe_studios.mvc.controllers.BaseViewController;
import rabe_studios.mvc.views.View;
import server.control.ServerControl;
import views.pages.HomeView;

public class HomeController extends BaseViewController {

	ServerControl server = null;

	public HomeController(BaseResource resource) {
		super(resource);
	}

	@Override
	protected View createView() {
		HomeView homeView = new HomeView();
		homeView.addStartButtonListener(e -> {
			server = new ServerControl(null);
			try {
				server.start(homeView.getPort());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		return homeView;
	}

}
