package com.scurab.web.drifmaps.client.component;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.scurab.web.drifmaps.shared.interfaces.IsDetailItem;

public class DetailItem<T extends IsDetailItem> extends Composite
{

	private static DetailItemUiBinder uiBinder = GWT.create(DetailItemUiBinder.class);
	@UiField Image imgClose;
	@UiField Label lblWhat;
	@UiField Label lblDetail;
	@UiField Label lblTime;
	T item;
	private static DateTimeFormat format = DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM);
	
	interface DetailItemUiBinder extends UiBinder<Widget, DetailItem>
	{
	}

	public DetailItem()
	{
		initWidget(uiBinder.createAndBindUi(this));
		sinkEvents(Event.ONCLICK);
	}
	
	public DetailItem(T d)
	{
		this();
		if(d == null)
			throw new NullPointerException();
		setValue(d);
	}
	
	public Image getCloseButton()
	{
		return imgClose;
	}
	
	public void setWhat(String text)
	{
		lblWhat.setText(text);
		item.setWhat(text);
	}
	
	public void setTime(Date time)
	{
		lblTime.setText(format.format(time));
		item.setWhen(time);
	}
	
	public void setDetail(String detail)
	{
		lblDetail.setText(detail);
		item.setDetail(detail);
	}
	
	public void setValue(T d)
	{
		if(d == null)
			throw new NullPointerException();
		item = d;
		setWhat(d.getWhat());
		setDetail(d.getDetail());
		setTime(d.getWhen());
	}
		
	public T getValue()
	{
		return item;
	}
}
