package devPage.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.DOM;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.user.client.Window;



public class DevPage implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel.get("NavBar").add(new NavBar());
	}
}
