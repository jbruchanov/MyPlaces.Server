package com.scurab.web.drifmaps.client.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.scurab.web.drifmaps.client.controls.CommandButton;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class StarDialog extends Composite
{
	public interface OnStarClick
	{
		void onStarClick(String value);
	}
	private static StarDialogUiBinder uiBinder = GWT.create(StarDialogUiBinder.class);

	interface StarDialogUiBinder extends UiBinder<Widget, StarDialog>
	{
	}

	@UiField Grid grid = null;
	private static CloseableDialog db = null;
	private OnStarClick listener = null;
	
	public StarDialog(OnStarClick listener)
	{
		initWidget(uiBinder.createAndBindUi(this));
		this.listener = listener;
		init();
	}
	
	private void init()
	{
		for(int i = 0;i<grid.getRowCount();i++)
		{
			int cells = grid.getCellCount(i);
			for(int j = 0;j<cells;j++)
			{
				CommandButton cb = (CommandButton) grid.getWidget(i, j);
				cb.addClickHandler(new ClickHandler()
				{
					@Override
					public void onClick(ClickEvent event)
					{					
						db.hide();
						CommandButton src = (CommandButton) event.getSource();
						listener.onStarClick(src.getCommand());
					}
				});
			}
		}
	}
	
	public static void show(OnStarClick clickListener)
	{
		if(db == null)
		{
			 db = new CloseableDialog();
			StarDialog sd = new StarDialog(clickListener);
			db.add(sd);
			db.center();
			db.setAnimationEnabled(false);
			db.setAutoHideEnabled(false);
			db.setGlassEnabled(true);
		}
		db.show();
	}
}
