package com.scurab.web.drifmaps.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.scurab.web.drifmaps.client.AppConstants;

public class ContextItem<T> extends Composite
{

	private static ContextItemUiBinder uiBinder = GWT.create(ContextItemUiBinder.class);
	@UiField Image imgIcon;
	@UiField Image imgClose;
	@UiField Label lblText;
	
	public enum IconType
	{
		Pros,  Cons
	}

	interface ContextItemUiBinder extends UiBinder<Widget, ContextItem>{}

	public ContextItem()
	{
		initWidget(uiBinder.createAndBindUi(this));	
		sinkEvents(Event.ONCLICK);
	}
	
	public void setTextValue(String txt)
	{
		lblText.setText(txt);
	}
	
	public String getTextValue()
	{
		return lblText.getText();
	}
	
	public Image getCloseButton()
	{
		return imgClose;
	}
	
	public void setIcon(String url)
	{
		imgIcon.setUrl(url);
	}
	
	public void setIcon(IconType type)
	{		
		String result = null;
		switch(type)
		{
			case Cons:
				result = AppConstants.BiggerIcons.ICO_MINUS;
				break;
			case Pros:
				result = AppConstants.BiggerIcons.ICO_PLUS;
				break;
		}
		setIcon(result);
	}
}
