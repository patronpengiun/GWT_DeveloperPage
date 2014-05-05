package devPage.client;

import com.github.gwtbootstrap.client.ui.Brand;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Dropdown;
import com.github.gwtbootstrap.client.ui.NavForm;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.NavText;
import com.github.gwtbootstrap.client.ui.PasswordTextBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;


public class NavBar extends Composite{
	@UiField
	NavLink upload,update,delete,dashboard;
	
	@UiField
	Dropdown dropDown;
	
	@UiField
	Brand brand;
	
	interface NavBarUiBinder extends UiBinder<Widget, NavBar> {}
	
	private static NavBarUiBinder uiBinder = GWT.create(NavBarUiBinder.class);
	
	public NavBar(){
		
		UIConstants myConstants = GWT.create(UIConstants.class);
		
		initWidget(uiBinder.createAndBindUi(this));
		
		upload.addClickHandler(new ClickHandler(){
			@Override
	          public void onClick(ClickEvent event) {
					RootPanel.get("content").clear();
					RootPanel.get("content").add(new uploadGame());
	          }
		});
		
		update.addClickHandler(new ClickHandler(){
			@Override
	          public void onClick(ClickEvent event) {
					RootPanel.get("content").clear();
					RootPanel.get("content").add(new updateGame());
	          }
		});
		
		delete.addClickHandler(new ClickHandler(){
			@Override
	          public void onClick(ClickEvent event) {
					RootPanel.get("content").clear();
					RootPanel.get("content").add(new deleteGame());
	          }
		});
		
		upload.setText(myConstants.SubmitGame());
		update.setText(myConstants.UpdateGame());
		delete.setText(myConstants.DeleteGame());
		dropDown.setText(myConstants.GameConsole());
		brand.setText(myConstants.MainPageTitle());
		dashboard.setText(myConstants.DashBoard());
	}
	
}
