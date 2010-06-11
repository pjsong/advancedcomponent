package menu.homepage.topmenu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;

public class ShowPage implements EntryPoint {

	public void onModuleLoad() {
		MenuBar menu = new Topmenu().buildMenu();
	    RootPanel.get("topmenu").add(menu);
	}
}
