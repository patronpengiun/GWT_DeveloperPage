package devPage.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.DOM;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.base.DivWidget;
import com.google.gwt.user.client.Window;



public class DevPage implements EntryPoint {
	
	@Override
	public void onModuleLoad() {
		DivWidget imgDiv = new DivWidget();
		imgDiv.setId("welcome");
		imgDiv.add(new Image(((SiteImages)(GWT.create(SiteImages.class))).welcome()));
		
		
		RootPanel.get("NavBar").add(new NavBar());
		RootPanel.get("content").add(imgDiv);
	}
}
