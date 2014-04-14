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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;


public class uploadGame extends Composite {
	@UiField
	Button submitBtn;
	
	@UiField
	TextBox name,url,width,height;
	
	@UiField
	ListBox token,turnBase,AI;
	
	@UiField
	TextArea description;
	
	
	private static uploadGameUiBinder uiBinder = GWT.create(uploadGameUiBinder.class);

	interface uploadGameUiBinder extends UiBinder<Widget, uploadGame> {}
	
	public uploadGame() {
		initWidget(uiBinder.createAndBindUi(this));
		submitBtn.addClickHandler(new ClickHandler() {
			@Override
	          public void onClick(ClickEvent event) {
				SessionInfo info = SessionInfo.getSessionInfo();
				
				JSONObject data = new JSONObject();
				//data.put("userId",new JSONNumber(Double.parseDouble(info.getDevId())));
				data.put("userId",new JSONString(info.getDevId()));
				data.put("accessSignature",new JSONString(info.getSignature()));
				data.put("gameName",new JSONString(name.getValue()));
				data.put("url",new JSONString(url.getValue()));
				data.put("width",new JSONNumber(Double.parseDouble(width.getValue())));
				data.put("height",new JSONNumber(Double.parseDouble(height.getValue())));
				data.put("description",new JSONString(description.getValue()));
				JSONObject pics = new JSONObject();
				JSONArray shots = new JSONArray();
				shots.set(0,new JSONString("a.b.com"));
				pics.put("screenshots", shots);
				pics.put("icon", new JSONString("a.c.com"));
				data.put("pic",pics);
				
				String url = "http://3-dot-smg-server.appspot.com/games";
				
				final PromptDialog dialog = PromptDialog.getDialog();
				
				RequestBuilder builder = new RequestBuilder(RequestBuilder.POST,url); 
				try{
						builder.sendRequest(data.toString(), new RequestCallback(){
						public void onError(Request request, Throwable exception) {
							dialog.show("Oops", "Submission failed, please try again.");
						} 
						
						public void onResponseReceived(Request request, Response response) 
						{ 
							if (response.getStatusCode() == 200) { 
								JSONObject ret = (JSONObject)JSONParser.parseStrict(response.getText());
								if (ret.get("gameId") != null){
									String id = ret.get("gameId").toString();
									RootPanel.get("content").clear();
									dialog.show("","The game has been successfully sumbmitted. The ID for this game is " + id + ".");
								}
								else {
									if (ret.get("error") != null){
										String error = ((JSONString)ret.get("error")).stringValue();
										if (error.equals("GAME_EXISTS"))
											dialog.show("Oops", "The name fot the game is already occupied, please try another name.");
										else if (error.equals("MISSING_INFO"))
												dialog.show("Oops", "Required information is missing, please try again.");
										else if (error.equals("WRONG_ACCESS_SIGNATURE"))
												dialog.show("Oops", "Validation failed, please log in again.");
										else
											dialog.show("Oops", "Submission failed, please try again.");
									}
									else{
										dialog.show("Oops", "Submission failed, please try again.");
									}
								}
						    }
							else {
								dialog.show("Oops", "Submission failed, please try again.");
						    } 
						} 
					});
				}
				catch (RequestException e){
					dialog.show("Oops", "Submission failed, please try again.");
				}
	          }
		});	
		
	}
	
	
}
