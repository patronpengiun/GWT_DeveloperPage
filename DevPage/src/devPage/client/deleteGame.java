package devPage.client;

import java.util.ArrayList;

import com.github.gwtbootstrap.client.ui.Button;
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
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;

public class deleteGame extends Composite {
	@UiField
	HTMLPanel panel;
	
	@UiField
	FlexTable table;
	
	@UiField
	Paragraph noGameWarning;

	private static final Binder binder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, deleteGame> {
	}

	public deleteGame() {
		
		initWidget(binder.createAndBindUi(this));

		// get currnet developer's all games info
		JSONObject data = new JSONObject();
		SessionInfo info = SessionInfo.getSessionInfo();
		String url = "http://3-dot-smg-server.appspot.com/gameinfo/all?userId="
				+ info.getDevId() + "&accessSignature=" + info.getSignature();
		//String url = "http://2.smg-server.appspot.com/gameinfo/all";
		final PromptDialog dialog = PromptDialog.getDialog();

		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		try {
			builder.sendRequest(data.toString(), new RequestCallback() {
				public void onError(Request request, Throwable exception) {
					dialog.show("Oops","Getting your games' info failed, please try again.");
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
							dialog.show("Oops", "Could not parse JSON");
						}
					} else {
						dialog.show("Oops", "Could not access server.");
					}
				}
			});
		} catch (RequestException e) {
			dialog.show("Oops", "Login failed, please try again.");
		}

	}

	protected void updateTable(JSONArray array) {
		noGameWarning.setVisible(false);
		if(array.size() == 0){
			
			noGameWarning.setText("Sorry, you haven't submitted game.");
			noGameWarning.setVisible(true);
			panel.add(noGameWarning);
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

				table.setWidget(i + 1, 2, getDelButton(gameInfo.get("gameId").toString()));
			}
			panel.add(table);
		}

	}

	private Button getDelButton(String gameId) {
		final String gameID = gameId;

		Button delBtn = new Button("Delete", new ClickHandler() {
			public void onClick(ClickEvent event) {
				/**
				 * delete this game with gameId
				 */
				JSONObject data = new JSONObject();
				SessionInfo info = SessionInfo.getSessionInfo();
				String url = "http://2.smg-server.appspot.com/games/"  + gameID.substring(1, gameID.length()-1) 
						+ "?developerId=" + info.getDevId()
						+ "&accessSignature=" + info.getSignature();

				final PromptDialog dialog = PromptDialog.getDialog();

				RequestBuilder builder = new RequestBuilder(RequestBuilder.DELETE, url);
				try {
					builder.sendRequest("", new RequestCallback() {
						public void onError(Request request, Throwable exception) {
							dialog.show("Oops","Delet game failed, please try again.");
						}

						public void onResponseReceived(Request request,Response response) {
							if (200 == response.getStatusCode()) {

								// parse the response text into JSON
								JSONObject ret = (JSONObject) JSONParser.parseStrict(response.getText());

								if (ret.get("success") != null) {
									dialog.show("Success","The game has been deleted.");
									
									//table.clear();
									panel.clear();
									panel.add(new deleteGame());

								} else {
									dialog.show("Oops",
											"The game delete failed, please try later.");
								}

							} else {
								dialog.show("Oops", "Couldn't send the request.");
							}
						}
					});
				} catch (RequestException e) {
					dialog.show("Oops", "Login failed, please try again.");
				}

			}
		});
		return delBtn;
	}


}
