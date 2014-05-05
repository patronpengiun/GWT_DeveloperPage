package devPage.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Form;
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
	
	private String updateGameId;
	
	
	private static updateGameUiBinder uiBinder = GWT.create(updateGameUiBinder.class);

	interface updateGameUiBinder extends UiBinder<Widget, updateGame> {}
	
	public updateGame() {
		initWidget(uiBinder.createAndBindUi(this));
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
							Window.alert("Update failed, please try again.");
						} 
						
						public void onResponseReceived(Request request, Response response) 
						{ 
							if (response.getStatusCode() == 200) { 
								JSONObject ret = (JSONObject)JSONParser.parseStrict(response.getText());
								if (ret.get("success") != null){
									RootPanel.get("content").clear();
									Window.alert("The game has been successfully updated");
								}
								else {
									if (ret.get("error") != null){
										String error = ((JSONString)ret.get("error")).stringValue();
										if (error.equals("GAME_EXISTS"))
											Window.alert("The name fot the game is already occupied, please try another name.");
										else if (error.equals("MISSING_INFO"))
												Window.alert("Required information is missing, please try again.");
										else if (error.equals("WRONG_ACCESS_SIGNATURE"))
												Window.alert("Validation failed, please log in again.");
										else
											Window.alert("Update failed, please try again.");
									}
									else{
										Window.alert("Update failed, please try again.");
									}
								}
						    }
							else {
								Window.alert("Update failed, please try again.");
						    } 
						} 
					});
				}
				catch (RequestException e){
					Window.alert("Update failed, please try again.");
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
					Window.alert("Getting your games' info failed, please try again.");
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
							Window.alert("No data available");
						}
					} else {
						Window.alert("Could not access server.");
					}
				}
			});
		} catch (RequestException e) {
			Window.alert("Getting your games' info failed, please try again.");
		}

	}
	
	protected void updateTable(JSONArray array) {
		noGameWarning.setVisible(false);
		if(array.size() == 0){
			
			noGameWarning.setText("Sorry, you haven't submitted game.");
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
									Window.alert("The game delete failed, please try later.");
								}

							} else {
								Window.alert("Couldn't send the request.");
							}
						}
					});
				} catch (RequestException e) {
					Window.alert("Login failed, please try again.");
				}

			}
		});
		return updateBtn;
	}
	
}
