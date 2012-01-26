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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Image;
import com.scurab.web.drifmaps.client.AppConstants.BigIcons;

public class NotificationDialog extends Composite
{
	public enum NotificationType
	{
		Information,
		Warning,
		Error
	}
	
	private static InputDialogUiBinder uiBinder = GWT.create(InputDialogUiBinder.class);
	@UiField Button btnOk;
	@UiField Label lblMessage;
	@UiField Label lblMainMessage;
	@UiField Image imgIcon;

	interface InputDialogUiBinder extends UiBinder<Widget, NotificationDialog>
	{
	}
	
	DialogBox db = new DialogBox();

	private NotificationDialog(String title, String message, NotificationType type)
	{
		initWidget(uiBinder.createAndBindUi(this));
		bind();
		
		imgIcon.setAltText(type.toString());
		imgIcon.setUrl(getImageUrl(type));
		if(title == null)			
			lblMessage.setVisible(false);
		else
			lblMessage.setText(title);
		lblMainMessage.setText(message);
		
		db.center();
		db.setAnimationEnabled(false);
		db.setAutoHideEnabled(false);
		db.setGlassEnabled(true);
		db.add(this);		
		db.show();
	}
	
	private void bind()
	{
		btnOk.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{			
				db.hide();
			}
		});
	}
	
	public static void show(String message)
	{
		new NotificationDialog(null, message, NotificationType.Information);
	}
	
	public static void show(String message, NotificationType type)
	{
		new NotificationDialog(null, message, type);
	}
	
	public static void show(String title, String message, NotificationType type)
	{
		new NotificationDialog(title, message, type);
	}
	
	public static void show(Throwable e)
	{
		if(e.getCause() != null)
			new NotificationDialog(e.getClass().toString(), e.getMessage() + "<br />" + e.getCause().getMessage(), NotificationType.Error);
		else
			new NotificationDialog(e.getClass().toString(), e.getMessage(), NotificationType.Error);
	}
	
	private static final String getImageUrl(NotificationType type)
	{
		switch(type)
		{
			case Error:
				return BigIcons.ICO_ERROR;
			case Warning:
				return BigIcons.ICO_ALERT;
			case Information:
			default:
				return BigIcons.ICO_INFO;
		}
	}

}
