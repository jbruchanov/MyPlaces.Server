package com.scurab.web.drifmaps.client.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;

public class InputDialog extends Composite
{
	public interface OnInputDialogButtonClick
	{
		void onOkClick(String value);		
		void onCancelClick();
	}
	private static InputDialogUiBinder uiBinder = GWT.create(InputDialogUiBinder.class);
	@UiField Button btnOk;
	@UiField Button btnCancel;
	@UiField TextBox txtInput;
	@UiField Label lblMessage;
	@UiField Image icon;

	interface InputDialogUiBinder extends UiBinder<Widget, InputDialog>
	{
	}
	
	DialogBox db = new DialogBox();

	private InputDialog(String message, SafeUri iconUri, OnInputDialogButtonClick listener)
	{
		initWidget(uiBinder.createAndBindUi(this));
		bind(listener);
		lblMessage.setText(message);
		db.center();
		db.setAnimationEnabled(false);
		db.setAutoHideEnabled(false);
		db.setGlassEnabled(true);
		if(iconUri != null)
			icon.setUrl(iconUri);
		db.add(this);		
		db.show();
	}
	
	private void bind(final OnInputDialogButtonClick listener)
	{
		btnOk.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{			
				db.hide();
				listener.onOkClick(txtInput.getText());
			}
		});
		
		btnCancel.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				db.hide();
				listener.onCancelClick();
			}
		});
	}
	
	public static void show(String message, OnInputDialogButtonClick listener)
	{
		new InputDialog(message, null, listener);
	}
	
	public static void show(SafeUri iconUrl, OnInputDialogButtonClick listener)
	{
		show(iconUrl, null, listener);
	}
	public static void show(SafeUri iconUrl, String message, OnInputDialogButtonClick listener)
	{
		new InputDialog(message, iconUrl, listener);
	}

}
