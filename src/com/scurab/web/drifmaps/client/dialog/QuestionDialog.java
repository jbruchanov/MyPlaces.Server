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
	@UiField Image icon;

	interface InputDialogUiBinder extends UiBinder<Widget, QuestionDialog>
	{
	}
	
	DialogBox db = new DialogBox();

	private QuestionDialog(String message, OnQuestionDialogButtonClick listener)
	{
		this(null,message,listener);		
	}
	
	private QuestionDialog(SafeUri icoUrl, String message,  OnQuestionDialogButtonClick listener)
	{
		initWidget(uiBinder.createAndBindUi(this));
		bind(listener);
		lblMessage.setText(message);
		btnCancel.setVisible(false);
		db.center();
		db.setAnimationEnabled(false);
		db.setAutoHideEnabled(false);
		db.setGlassEnabled(true);
		if(icoUrl != null)
			icon.setUrl(icoUrl);
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
	
	public static void show(SafeUri safeHtml, OnQuestionDialogButtonClick listener)
	{		
		show(safeHtml, null ,listener);
	}
	
	public static void show(SafeUri safeHtml, String message,  OnQuestionDialogButtonClick listener)
	{		
		new QuestionDialog(safeHtml, message ,listener);
	}

}
