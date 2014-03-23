package devPage.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestException;


public class updateGame extends Composite {
	@UiField
	Button updateBtn, backBtn;
	
	@UiField
	TextBox name,url,width,length;
	
	@UiField
	ListBox number,turnBase,AI;
	
	@UiField
	TextArea description;
	
	
	private static updateGameUiBinder uiBinder = GWT.create(updateGameUiBinder.class);

	interface updateGameUiBinder extends UiBinder<Widget, updateGame> {}
	
	public updateGame() {
		initWidget(uiBinder.createAndBindUi(this));
		updateBtn.addClickHandler(new ClickHandler() {
			@Override
	          public void onClick(ClickEvent event) {
					//update();
	          }
		});	
		
		backBtn.addClickHandler(new ClickHandler() {
			@Override
	          public void onClick(ClickEvent event) {
					//update();
	          }
		});	
		
	}
	
	private String serverURL="temp.sever.com/games";
	
	/*private String getData(){
		JSONObject data = new JSONObject();
		data.put("gameName",new JSONString(name.getValue()));
	}*/
	

	/*private void update(){
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST,serverURL); 
		Request updateRequest; 
		try { 
			//updateRequest = builder.sendRequest(data, storeCallbaclHandler); 
		}
	 catch (RequestException e) { 
	         Window.alert("Error:"+ e); 
	 } 
	}*/
	
}
