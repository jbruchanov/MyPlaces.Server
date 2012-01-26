package com.scurab.web.drifmaps.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;

public class Menu extends Composite
{

	private static MenuUiBinder uiBinder = GWT.create(MenuUiBinder.class);
	@UiField Button btnAdd;
	@UiField Button btnSave;
	@UiField DisclosurePanel menuContentHolder;

	interface MenuUiBinder extends UiBinder<Widget, Menu>
	{
	}

	public Menu()
	{
		initWidget(uiBinder.createAndBindUi(this));
		//to hide triangle button to open/close disclosure panel
		menuContentHolder.setHeader(new Label());
		menuContentHolder.remove(menuContentHolder.getHeader());
	}
	
	public Button getAddButton()
	{
		return btnAdd;
	}
	
	public Button getSaveButton()
	{
		return btnSave;
	}
	
	public DisclosurePanel getMenuContent()
	{
		return menuContentHolder;
	}

}
