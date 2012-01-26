package com.scurab.web.drifmaps.client.dialog;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.datepicker.client.DateBox;
import com.scurab.web.drifmaps.shared.datamodel.Detail;

public class ContextDetailInputDialog extends Composite
{
	public interface OnInputDialogButtonClick
	{
		void onOkClick(Detail d);		
		void onCancelClick();
	}
	private static InputDialogUiBinder uiBinder = GWT.create(InputDialogUiBinder.class);
	@UiField Button btnOk;
	@UiField Button btnCancel;
	@UiField TextBox txtHeader;
	@UiField TextArea txtDetail;
	@UiField DateBox txtDate;	

	interface InputDialogUiBinder extends UiBinder<Widget, ContextDetailInputDialog>
	{
	}
	
	DialogBox db = new DialogBox();

	private ContextDetailInputDialog(String message, String detail, Date date, OnInputDialogButtonClick listener)
	{
		initWidget(uiBinder.createAndBindUi(this));
		bind(listener);		
		txtDate.setFormat(new DateBox.DefaultFormat(DateTimeFormat.getShortDateFormat()));
		txtDate.setValue(date);
		txtHeader.setText(message);
		txtDetail.setText(detail);
		db.center();
		db.setAnimationEnabled(false);
		db.setAutoHideEnabled(false);
		db.setGlassEnabled(true);
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
				Detail d = new Detail();
				d.setWhat(txtHeader.getText());
				d.setDetail(txtDetail.getText());
				d.setTime(txtDate.getValue());
				if(!(d.getWhat().trim().length() == 0
					|| d.getDetail().trim().length() == 0
					|| d.getTime() ==  null))
				{
					db.hide();
					listener.onOkClick(d);
				}
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
	
	public static void show(String message, String details, Date date, OnInputDialogButtonClick listener)
	{
		new ContextDetailInputDialog(message, details, date, listener);
	}
}
