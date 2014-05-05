package devPage.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.github.gwtbootstrap.client.ui.TextArea;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.appengine.api.memcache.MemcacheServicePb.MemcacheDeleteRequest.Item;
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
	TextBox name,url;
	
	@UiField
	ListBox token,turnBase,AI;
	
	@UiField
	TextArea description;
	
	@UiField
	Heading head;
	
	@UiField
	Paragraph descrip;
	
	@UiField
	ControlLabel gameName, ifToken, turnBased, gameURL, ifAI, pic, gameDetail;
	
	
	
	private static uploadGameUiBinder uiBinder = GWT.create(uploadGameUiBinder.class);

	interface uploadGameUiBinder extends UiBinder<Widget, uploadGame> {}
	
	public uploadGame() {
		
		initWidget(uiBinder.createAndBindUi(this));
		
		
		final UIConstants myConstants = GWT.create(UIConstants.class);
		submitBtn.setText(myConstants.SubmitGame());
		head.setText(myConstants.SubmitGame());
		descrip.setText(myConstants.SubmitSubHead());
		
		
		gameName.getElement().setInnerText(myConstants.gameName());
		ifToken.getElement().setInnerText(myConstants.ifToken());
		turnBased.getElement().setInnerText(myConstants.turnBased());
		gameURL.getElement().setInnerText(myConstants.gameURL());
		ifAI.getElement().setInnerText(myConstants.ifAI());
		pic.getElement().setInnerText(myConstants.pic());
		gameDetail.getElement().setInnerText(myConstants.gameDetail());
		token.setItemText(0, myConstants.yes());
		token.setItemText(1, myConstants.no());
		turnBase.setItemText(0, myConstants.yes());
		turnBase.setItemText(1, myConstants.no());
		AI.setItemText(0, myConstants.yes());
		AI.setItemText(1, myConstants.no());
		
		
		submitBtn.addClickHandler(new ClickHandler() {
			@Override
	          public void onClick(ClickEvent event) {
				SessionInfo info = SessionInfo.getSessionInfo();
				
				JSONObject data = new JSONObject();
				//data.put("userId",new JSONNumber(Double.parseDouble(info.getDevId())));
				data.put("developerId",new JSONString(info.getDevId()));
				data.put("accessSignature",new JSONString(info.getSignature()));
				data.put("gameName",new JSONString(name.getValue()));
				data.put("url",new JSONString(url.getValue()));
				//data.put("width",new JSONNumber(Double.parseDouble(width.getValue())));
				//data.put("height",new JSONNumber(Double.parseDouble(height.getValue())));
				data.put("description",new JSONString(description.getValue()));
				JSONObject pics = new JSONObject();
				JSONArray shots = new JSONArray();
				shots.set(0,new JSONString("a.b.com"));
				pics.put("screenshots", shots);
				pics.put("icon", new JSONString("a.c.com"));
				data.put("pic",pics);
				
				String url = "http://smg-server.appspot.com/games";
				
				RequestBuilder builder = new RequestBuilder(RequestBuilder.POST,url); 
				try{
						builder.sendRequest(data.toString(), new RequestCallback(){
						public void onError(Request request, Throwable exception) {
							Window.alert(myConstants.submisstionFail());
						} 
						
						public void onResponseReceived(Request request, Response response) 
						{ 
							if (response.getStatusCode() == 200) { 
								JSONObject ret = (JSONObject)JSONParser.parseStrict(response.getText());
								if (ret.get("gameId") != null){
									String id = ret.get("gameId").toString();
									RootPanel.get("content").clear();
									Window.alert(myConstants.submissionSuc() + id + ".");
								}
								else {
									if (ret.get("error") != null){
										String error = ((JSONString)ret.get("error")).stringValue();
										if (error.equals("GAME_EXISTS"))
											Window.alert(myConstants.nameOccupied());
										else if (error.equals("MISSING_INFO"))
											Window.alert(myConstants.inforMiss());
										else if (error.equals("WRONG_ACCESS_SIGNATURE"))
											Window.alert(myConstants.validFail());
										else
											Window.alert(myConstants.submisstionFail());
									}
									else{
										Window.alert(myConstants.submisstionFail());
									}
								}
						    }
							else {
								Window.alert(myConstants.submisstionFail());
						    } 
						} 
					});
				}
				catch (RequestException e){
					Window.alert(myConstants.submisstionFail());
				}
	          }
		});	
		
		
		
	}
	
	
}
