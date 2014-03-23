package devPage.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;


public class NavBar extends Composite{
	@UiField
	NavLink account,upload,update,delete,dashboard,database;
	
	@UiField
	Button upBtn,inBtn;
	
	interface NavBarUiBinder extends UiBinder<Widget, NavBar> {}
	
	private static NavBarUiBinder uiBinder = GWT.create(NavBarUiBinder.class);
	
	public NavBar(){
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
		
		upBtn.addClickHandler(new ClickHandler(){
			@Override
	          public void onClick(ClickEvent event) {
					SignUpDialog dialog = new SignUpDialog();
					RootPanel.get().add(dialog);
					dialog.showModal();
	          }
		});
		
	}
	
}
