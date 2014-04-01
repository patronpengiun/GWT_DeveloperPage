package devPage.client;

import com.github.gwtbootstrap.client.ui.Button;
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
import com.google.gwt.user.client.Window;
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
	
	@UiField
	TextBox name;
	
	@UiField
	PasswordTextBox password;
	
	@UiField
	NavText welcome;
	
	interface NavBarUiBinder extends UiBinder<Widget, NavBar> {}
	
	private static NavBarUiBinder uiBinder = GWT.create(NavBarUiBinder.class);
	
	public NavBar(){
		initWidget(uiBinder.createAndBindUi(this));
		
		setVisible(welcome.getElement(),false);
		
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
		
		upBtn.addClickHandler(new ClickHandler(){
			@Override
	          public void onClick(ClickEvent event) {
					RootPanel.get("signup-modal").clear();
					SignUpDialog dialog = new SignUpDialog();
					RootPanel.get("signup-modal").add(dialog);
					dialog.showModal();
	          }
		});
		
		inBtn.addClickHandler(new ClickHandler(){
			@Override
	          public void onClick(ClickEvent event) {
				JSONObject data = new JSONObject();
				
				String url = "http://2-dot-smg-server.appspot.com/developers/" + name.getValue() + "?password=" + password.getValue();
				
				final PromptDialog dialog = PromptDialog.getDialog();
				
				RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,url); 
				try{
						builder.sendRequest(data.toString(), new RequestCallback(){
						public void onError(Request request, Throwable exception) { 
							dialog.show("Oops", "Login failed, please try again.");
						} 
						
						public void onResponseReceived(Request request, Response response) 
						{ 
							if (response.getStatusCode() == 200) { 
								JSONObject ret = (JSONObject)JSONParser.parseStrict(response.getText());
								if (ret.get("accessSignature") != null){
									String id = name.getValue();
									String devName = "";
									if (ret.get("nickname") != null && !((JSONString)ret.get("nickname")).stringValue().equals(""))
										devName = ((JSONString)ret.get("nickname")).stringValue();
									else {
										if (ret.get("firstName") != null)
											devName += ((JSONString)ret.get("firstName")).stringValue();
										if (ret.get("lastName") != null){
											if (!devName.equals("")) devName += " ";
											devName += ((JSONString)ret.get("lastName")).stringValue();
										}
									}
									String signature = ((JSONString)ret.get("accessSignature")).stringValue();
									
									SessionInfo info = SessionInfo.getSessionInfo();
									info.setDevId(id);
									info.setDevName(devName);
									info.setSignature(signature);
									
									dialog.show("", "Welcome back, " + devName + "!");
									((NavBar)(RootPanel.get("NavBar").getWidget(0))).showWelcome("Welcome back, " + devName);
								}
								else {
									if (ret.get("error") != null){
										String error = ((JSONString)ret.get("error")).stringValue();
										if (error.equals("WRONG_PASSWORD"))
											dialog.show("Oops", "Wrong password, please try again.");
										else {
											if (error.equals("WRONG_DEVELOPER_ID"))
												dialog.show("Oops", "Invalid ID, please try again.");
											else 
												dialog.show("Oops", "Login failed, please try again.");
										}
									}
									else{
										dialog.show("Oops", "Login failed, please try again.");
									}
								}
						    }
							else { 
								dialog.show("Oops", "Login failed, please try again.");
						    } 
						} 
					});
				}
				catch (RequestException e){
					dialog.show("Oops", "Login failed, please try again.");
				}
	          }
		});
		
	}
	
	public void showWelcome(String content){
		setVisible(upBtn.getElement(),false);
		setVisible(inBtn.getElement(),false);
		setVisible(name.getElement(),false);
		setVisible(password.getElement(),false);
		welcome.setText(content);
		setVisible(welcome.getElement(),true);
	}
	
}
