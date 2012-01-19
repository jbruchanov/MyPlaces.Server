package com.scurab.web.drifmaps.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.scurab.web.drifmaps.client.presenter.MainViewPresenter;
public class MainView extends Composite implements MainViewPresenter.Display
{
	private RootPanel top = null;
	private RootPanel menu = null;
	private RootPanel bottom = null;
	private RootPanel workSpace = null;
	
	public MainView()
	{
		top = RootPanel.get("top");
		menu = RootPanel.get("menu");
		workSpace = RootPanel.get("workspace");
		bottom = RootPanel.get("bottom");
	}
	
	public RootPanel getWorkSpace()
	{
		return workSpace;
	}

	@Override
	public RootPanel getMenu()
	{
		return menu;
	}
	
	public RootPanel getTop()
	{
		return top;
	}
}
