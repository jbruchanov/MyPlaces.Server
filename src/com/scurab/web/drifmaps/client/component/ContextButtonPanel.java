package com.scurab.web.drifmaps.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ContextButtonPanel extends Composite
{

	private static ContextButtonPanelUiBinder uiBinder = GWT.create(ContextButtonPanelUiBinder.class);

	interface ContextButtonPanelUiBinder extends UiBinder<Widget, ContextButtonPanel>
	{
	}
	
	@UiField Image btnPlus;
	@UiField Image btnMinus;
	@UiField Image btnCustom;

	public ContextButtonPanel()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public HasClickHandlers getPlusButton()
	{
		return btnPlus;
	}
	
	public HasClickHandlers getMinusButton()
	{
		return btnMinus;
	}
	
	public HasClickHandlers getCustomButton()
	{
		return btnCustom;
	}

}
