package devPage.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ControlLabel;
import com.github.gwtbootstrap.client.ui.Form;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.Paragraph;
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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;


public class updateGame extends Composite {
	@UiField
	FlexTable table;
	
	@UiField
	Paragraph noGameWarning;
	
	@UiField
	Form form;
	
	@UiField
	Button updateBtn, backBtn;
	
	@UiField
	TextBox name,gameurl;
	
	@UiField
	ListBox number,turnBase,AI;
	
	@UiField
	TextArea description;
	
	@UiField
	Heading headUpd;
	
	@UiField
	Paragraph subheadUpd;
	
	
	@UiField
	ControlLabel gameName, playerNum, turnBased, gameURL, ifAI, pic, gameDetail;
	
	private String updateGameId;
	
	final UIConstants myConstants = GWT.create(UIConstants.class);
	private static updateGameUiBinder uiBinder = GWT.create(updateGameUiBinder.class);

	interface updateGameUiBinder extends UiBinder<Widget, updateGame> {}
	
	public updateGame() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
		headUpd.setText(myConstants.headUpd());
		subheadUpd.setText(myConstants.subheadUpd());
		updateBtn.setText(myConstants.upDate());
		backBtn.setText(myConstants.back());
		
		gameName.getElement().setInnerText(myConstants.gameName());
		playerNum.getElement().setInnerText(myConstants.playerNum());
		turnBased.getElement().setInnerText(myConstants.turnBased());
		gameURL.getElement().setInnerText(myConstants.gameURL());
		ifAI.getElement().setInnerText(myConstants.ifAI());
		pic.getElement().setInnerText(myConstants.pic());
		gameDetail.getElement().setInnerText(myConstants.gameDetail());

		turnBase.setItemText(0, myConstants.yes());
		turnBase.setItemText(1, myConstants.no());
		AI.setItemText(0, myConstants.yes());
		AI.setItemText(1, myConstants.no());
		
		
		
		updateBtn.addClickHandler(new ClickHandler() {
			@Override
	          public void onClick(ClickEvent event) {
				SessionInfo info = SessionInfo.getSessionInfo();
				
				JSONObject data = new JSONObject();
				Double _id = Double.parseDouble(info.getDevId());
				data.put("developerId",new JSONString(_id.toString()));
				//data.put("userId",new JSONString(info.getDevId()));
				data.put("accessSignature",new JSONString(info.getSignature()));
				data.put("gameName",new JSONString(name.getValue()));
				data.put("url",new JSONString(gameurl.getValue()));
				//data.put("width",new JSONNumber(Double.parseDouble(width.getValue())));
				//data.put("height",new JSONNumber(Double.parseDouble(length.getValue())));
				data.put("description",new JSONString(description.getValue()));
				
				String url = "http://smg-server.appspot.com/games/" + updateGameId;
				
				RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT,url); 
				try{
						builder.sendRequest(data.toString(), new RequestCallback(){
						public void onError(Request request, Throwable exception) {
							Window.alert(myConstants.updateFail());
						} 
						
						public void onResponseReceived(Request request, Response response) 
						{ 
							if (response.getStatusCode() == 200) { 
								JSONObject ret = (JSONObject)JSONParser.parseStrict(response.getText());
								if (ret.get("success") != null){
									RootPanel.get("content").clear();
									Window.alert(myConstants.updateSuc());
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
											Window.alert(myConstants.updateFail());
									}
									else{
										Window.alert(myConstants.updateFail());
									}
								}
						    }
							else {
								Window.alert(myConstants.updateFail());
						    } 
						} 
					});
				}
				catch (RequestException e){
					Window.alert(myConstants.updateFail());
				}
	          }
		});	
		
		backBtn.addClickHandler(new ClickHandler() {
			@Override
	          public void onClick(ClickEvent event) {
					//update();
	          }
		});	
		
		JSONObject data = new JSONObject();
		SessionInfo info = SessionInfo.getSessionInfo();
		String url = "http://smg-server.appspot.com/gameinfo/all?developerId="
				+ info.getDevId() + "&accessSignature=" + info.getSignature();
		//String url = "http://2.smg-server.appspot.com/gameinfo/all";

		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
			builder.sendRequest(data.toString(), new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					Window.alert(myConstants.getInfoFail());
				}

				public void onResponseReceived(Request request,
						Response response) {
					if (200 == response.getStatusCode()) {
						try {
							// parse the response text into JSON
							JSONValue jsonValue = JSONParser
									.parseStrict(response.getText());
							JSONArray jsonArray = jsonValue.isArray();

							if (jsonArray != null) {
								updateTable(jsonArray);

							} else {
								throw new JSONException();
							}
						} catch (JSONException e) {
							Window.alert(myConstants.noData());
						}
					} else {
						Window.alert(myConstants.accessServerFail());
					}
				}
			});
		} catch (RequestException e) {
			Window.alert(myConstants.getInfoFail());
		}

	}
	
	protected void updateTable(JSONArray array) {
		noGameWarning.setVisible(false);
		if(array.size() == 0){
			
			noGameWarning.setText(myConstants.noGame());
			noGameWarning.setVisible(true);
			//panel.add(noGameWarning);
		}
		else{
			table.insertRow(0);
			table.setText(0, 0, "Game ID");
			table.setText(0, 1, "Game Name");
			table.setText(0, 2, "");
			
			JSONObject gameInfo;

			for (int i = 0; i < array.size(); i++) {
				table.insertRow(i+1);
				gameInfo = (JSONObject) array.get(i);
				if (gameInfo.get("gameId") != null) {
					table.setText(i + 1, 0, gameInfo.get("gameId").toString());
				}
				if (gameInfo.get("gameName") != null) {
					table.setText(i + 1, 1, gameInfo.get("gameName").toString());
				}

				table.setWidget(i + 1, 2, getUpdateButton(gameInfo.get("gameId").toString()));
			}
			//panel.add(table);
		}
	}
	
	private Button getUpdateButton(String gameId) {
		final String gameID = gameId;
		updateGameId = gameId.substring(1,gameId.length()-1);

		Button updateBtn = new Button("Update", new ClickHandler() {
			public void onClick(ClickEvent event) {
				/**
				 * delete this game with gameId
				 */
				JSONObject data = new JSONObject();
				SessionInfo info = SessionInfo.getSessionInfo();
				String url = "http://smg-server.appspot.com/games/"  + gameID.substring(1, gameID.length()-1);

				RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
				try {
					builder.sendRequest("", new RequestCallback() {
						public void onError(Request request, Throwable exception) {
							Window.alert("Delet game failed, please try again.");
						}

						public void onResponseReceived(Request request,Response response) {
							if (200 == response.getStatusCode()) {

								// parse the response text into JSON
								JSONObject ret = (JSONObject) JSONParser.parseStrict(response.getText());

								if (ret.get("developerId") != null) {
									name.setText(((JSONString)ret.get("gameName")).stringValue());
									description.setText(((JSONString)ret.get("description")).stringValue());
									gameurl.setText(((JSONString)ret.get("url")).stringValue());
								} else {
									Window.alert(myConstants.deleteFail());
								}

							} else {
								Window.alert(myConstants.cannotSendReq());
							}
						}
					});
				} catch (RequestException e) {
					Window.alert(myConstants.loginFail());
				}

			}
		});
		return updateBtn;
	}
	
}
