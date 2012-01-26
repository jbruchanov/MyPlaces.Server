package com.scurab.web.drifmaps.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.scurab.web.drifmaps.client.DrifMaps;
import com.scurab.web.drifmaps.shared.interfaces.IsDetailItem;

public class DetailItem<T extends IsDetailItem> extends Composite
{

	private static DetailItemUiBinder uiBinder = GWT.create(DetailItemUiBinder.class);
	@UiField Image imgClose;
	@UiField Label lblWhat;
	@UiField Label lblDetail;
	@UiField Label lblTime;
	T item;
	
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
		setValue(d);
	}
	
	public Image getCloseButton()
	{
		return imgClose;
	}
	
	public void setWhat(String text)
	{
		lblWhat.setText(text);
	}
	
	public void setTime(String time)
	{
		lblTime.setText(time);
	}
	
	public void setDetail(String detail)
	{
		lblDetail.setText(detail);
	}
	
	public void setValue(T d)
	{
		setWhat(d.getWhat());
		setDetail(d.getDetail());
		setTime(DrifMaps.DateFormat.format(d.getWhen()));
	}
		
	public T getValue()
	{
		return item;
	}
}
