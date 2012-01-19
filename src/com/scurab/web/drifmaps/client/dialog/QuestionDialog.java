package com.scurab.web.drifmaps.client.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;

public class QuestionDialog extends Composite
{
	public interface OnQuestionDialogButtonClick
	{
		void onYesClick();
		void onNoClick();
		void onCancelClick();
	}
	private static InputDialogUiBinder uiBinder = GWT.create(InputDialogUiBinder.class);
	@UiField Button btnYes;
	@UiField Button btnCancel;	
	@UiField Button btnNo;
	@UiField Label lblMessage;

	interface InputDialogUiBinder extends UiBinder<Widget, QuestionDialog>
	{
	}
	
	DialogBox db = new DialogBox();

	private QuestionDialog(String message, OnQuestionDialogButtonClick listener)
	{
		initWidget(uiBinder.createAndBindUi(this));
		bind(listener);
		lblMessage.setText(message);
		db.center();
		db.setAnimationEnabled(false);
		db.setAutoHideEnabled(false);
		db.setGlassEnabled(true);
		db.add(this);		
		db.show();		
	}
	
	private void bind(final OnQuestionDialogButtonClick listener)
	{
		btnYes.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{					
				db.hide();
				listener.onYesClick();
			}
		});
		
		btnNo.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{					
				db.hide();
				listener.onNoClick();
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
	
	public static void show(String message, OnQuestionDialogButtonClick listener)
	{
		new QuestionDialog(message,listener);
	}

}
