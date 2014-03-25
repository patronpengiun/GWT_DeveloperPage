package devPage.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Modal;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;


public class PromptDialog extends Composite{
	@UiField
	Modal prompt;
	
	@UiField
	Label text;
	
	@UiField
	Button btn;
	
	private static PromptDialogUiBinder uiBinder = GWT.create(PromptDialogUiBinder.class);

	interface PromptDialogUiBinder extends UiBinder<Widget, PromptDialog> {}
	
	private PromptDialog() {
		initWidget(uiBinder.createAndBindUi(this));
		
		btn.addClickHandler(new ClickHandler(){
			@Override
	          public void onClick(ClickEvent event) {
					prompt.hide();
	          }
		});
	}
	
	private static PromptDialog dialog;
	
	public static PromptDialog getDialog(){
		if (null == dialog) 
			dialog = new PromptDialog();
		return dialog;
	}
	
	public void show(String header, String content) {
		prompt.setTitle(header);
		text.setText(content);
		prompt.show();
	}
	
	
}
	
