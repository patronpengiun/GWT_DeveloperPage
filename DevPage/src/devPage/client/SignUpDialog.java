package devPage.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.PasswordTextBox;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class SignUpDialog extends Composite{
	@UiField
	Modal signUpModal;
	
	@UiField
	TextBox email,fname,mname,lname,nickname;
	
	@UiField
	PasswordTextBox password;
	
	@UiField
	Button createBtn,backBtn;
	
	private static SignUpDialogUiBinder uiBinder = GWT.create(SignUpDialogUiBinder.class);

	interface SignUpDialogUiBinder extends UiBinder<Widget, SignUpDialog> {}
	
	public SignUpDialog() {
		initWidget(uiBinder.createAndBindUi(this));
		
		backBtn.addClickHandler(new ClickHandler(){
			@Override
	          public void onClick(ClickEvent event) {
					signUpModal.hide();
	          }
		});
		
		createBtn.addClickHandler(new ClickHandler(){
			@Override
	          public void onClick(ClickEvent event) {
				JSONObject data = new JSONObject();
				data.put("email", new JSONString(email.getValue()));
				data.put("password",new JSONString(password.getValue()));
				data.put("firstName", new JSONString(fname.getValue()));
				data.put("middleName", new JSONString(mname.getValue()));
				data.put("lastName", new JSONString(lname.getValue()));
				data.put("nickname", new JSONString(nickname.getValue()));
				
				String url = "http://3-dot-smg-server.appspot.com/user";
				
				final PromptDialog dialog = PromptDialog.getDialog();
				
				RequestBuilder builder = new RequestBuilder(RequestBuilder.POST,url); 
				try{
						builder.sendRequest(data.toString(), new RequestCallback(){
						public void onError(Request request, Throwable exception) {
							signUpModal.hide();
							dialog.show("Oops", "Sign-up failed, please try again.");
						} 
						
						public void onResponseReceived(Request request, Response response) 
						{ 
							if (response.getStatusCode() == 200) { 
								JSONObject ret = (JSONObject)JSONParser.parseStrict(response.getText());
								if (ret.get("accessSignature") != null){
									String id = ret.get("userId").toString();
									String devName = "";
									if (!nickname.getValue().equals(""))
										devName = nickname.getValue();
									else 
										devName = fname.getValue() + " " + lname.getValue();
									String signature = ((JSONString)ret.get("accessSignature")).stringValue();
									
									SessionInfo info = SessionInfo.getSessionInfo();
									info.setDevId(id);
									info.setDevName(devName);
									info.setSignature(signature);
									
									((NavBar)(RootPanel.get("NavBar").getWidget(0))).showWelcome("Welcome, " + devName);
									
									signUpModal.hide();
									dialog.show("Welcome", "Dear " + devName + ", you have successfully created a developer account. You developer ID is " + id + ".");
								}
								else {
									if (ret.get("error") != null){
										String error = ((JSONString)ret.get("error")).stringValue();
										if (error.equals("EMAIL_EXISTS")){
											dialog.show("Oops", "The email is already used, please try again.");
											signUpModal.hide();
										}
										else {
											signUpModal.hide();
											if (error.equals("MISSING_INFO"))
												dialog.show("Oops", "Required information is missing, please try again.");
											else 
												dialog.show("Oops", "Sign-up failed, please try again.");
										}
									}
									else{
										signUpModal.hide();
										dialog.show("Oops", "Sign-up failed, please try again.");
									}
								}
						    }
							else {
								signUpModal.hide();
								dialog.show("Oops", "Sign-up failed, please try again.");
						    } 
						} 
					});
				}
				catch (RequestException e){
					signUpModal.hide();
					dialog.show("Oops", "Sign-up failed, please try again.");
				}
	          }
		});
	}
	
	public void showModal(){
		signUpModal.show();
	}
}
