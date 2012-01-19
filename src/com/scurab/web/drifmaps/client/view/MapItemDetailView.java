package com.scurab.web.drifmaps.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.drifmaps.client.form.MapItemDetailForm;
import com.scurab.web.drifmaps.client.formmodel.MapItemDetailFormModel;
import com.scurab.web.drifmaps.client.presenter.MapItemDetailViewPresenter;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;

public class MapItemDetailView extends Composite implements MapItemDetailViewPresenter.Display
{
	private static MapItemDetailViewUiBinder uiBinder = GWT.create(MapItemDetailViewUiBinder.class);

	interface MapItemDetailViewUiBinder extends UiBinder<Widget, MapItemDetailView>
	{
	}

	@UiField Button btnSave;
	@UiField HTMLPanel contentHolder;
	private MapItemDetailForm mForm = null;
	private MapItemDetailFormModel mFormModel = null;
	
	
	public MapItemDetailView()
	{
		initWidget(uiBinder.createAndBindUi(this));
		init();
	}
	
	private void init()
	{
		mFormModel = new MapItemDetailFormModel(); 
		mForm = new MapItemDetailForm(mFormModel);
		contentHolder.add(mForm);
	}

	@Override
	public Button getSaveButton()
	{
		return btnSave;
	}

	@Override
	public void setModel(MapItem item)
	{
		mFormModel.setValue(item);
	}

	@Override
	public MapItem getModel()
	{
		return mFormModel.getValue();
	}
}
