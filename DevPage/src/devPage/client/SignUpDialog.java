package devPage.client;

import com.github.gwtbootstrap.client.ui.Modal;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SignUpDialog extends Composite{
	@UiField
	Modal signUpModal;
	
	private static SignUpDialogUiBinder uiBinder = GWT.create(SignUpDialogUiBinder.class);

	interface SignUpDialogUiBinder extends UiBinder<Widget, SignUpDialog> {}
	
	public SignUpDialog() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void showModal(){
		signUpModal.show();
	}
}
