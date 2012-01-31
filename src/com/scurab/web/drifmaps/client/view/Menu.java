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
	@UiField Button btnLeft;
	@UiField Button btnRight;
	@UiField Button btnStar;
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
		
		btnRight.setEnabled(false);
		btnRight.setVisible(false);
	}
	
	public Button getAddButton()
	{
		return btnLeft;
	}
	
	public Button getSaveButton()
	{
		return btnRight;
	}
	
	public Button getStarButton()
	{
		return btnStar;
	}
	
	public DisclosurePanel getMenuContent()
	{
		return menuContentHolder;
	}

}
