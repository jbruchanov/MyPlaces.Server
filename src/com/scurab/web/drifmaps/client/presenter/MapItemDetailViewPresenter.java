package com.scurab.web.drifmaps.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.drifmaps.client.DataServiceAsync;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;

public class MapItemDetailViewPresenter 
{
	public interface Display
	{
		Button getSaveButton();
		Widget asWidget();
		void setModel(MapItem item);
		MapItem getModel();
	}
	
	private DataServiceAsync mDataService = null;
	private HandlerManager mEventBus = null;
	private Display mDisplay = null;
	
	public MapItemDetailViewPresenter(DataServiceAsync dataService, HandlerManager eventBus, Display display)
	{
		mDataService = dataService;
		mEventBus = eventBus;
		mDisplay = display;
	}
	
	private void bind()
	{
		
	}
	
	public Widget asWidget()
	{
		return mDisplay.asWidget();
	}
}

