package devPage.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class devPageUiBinder extends Composite implements HasText {
	private static devPageUiBinderUiBinder uiBinder = GWT
			.create(devPageUiBinderUiBinder.class);

	interface devPageUiBinderUiBinder extends
			UiBinder<Widget, devPageUiBinder> {
	}
	
	public devPageUiBinder() {
		initWidget(uiBinder.createAndBindUi(this));
		// add widget handlers here
	}
			
	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}
	
}
