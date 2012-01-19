package com.scurab.web.drifmaps.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.scurab.web.drifmaps.client.presenter.MainViewPresenter;
import com.scurab.web.drifmaps.client.view.MainView;
import com.scurab.web.drifmaps.language.Words;;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DrifMaps implements EntryPoint {
	
	public static final Words Words = GWT.create(Words.class);
	private static final DataServiceAsync sDataService = GWT.create(DataService.class);
	private static final HandlerManager sEventBus = new HandlerManager(null);
	
	@Override
	public void onModuleLoad() {
		try
		{
			//MainViewOld mv = new MainViewOld(sDataService,sEventBus);
			new MainViewPresenter(new MainView());
		}
		catch(Exception e)
		{
			Window.alert(e.getMessage());
		}
		
	}
}
