package com.scurab.web.drifmaps.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.maps.client.InfoWindow;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.scurab.web.drifmaps.client.presenter.MapItemDetailViewPresenter;
import com.scurab.web.drifmaps.client.view.MapItemDetailView;

public class MainViewOld extends Composite
{

	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
	@UiField
	Button btnShowMap;
	@UiField
	Button btnTest;
	@UiField
	HTMLPanel contentHolder;

	interface MainViewUiBinder extends UiBinder<Widget, MainViewOld>
	{
	}

	private MapWidget mapWidget;
	private DataServiceAsync mDataService = null;
	private HandlerManager mEventBus = null;

	public MainViewOld(DataServiceAsync dataService, HandlerManager eventBus)
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("btnTest")
	void onBtnTestClick(ClickEvent event)
	{
		contentHolder.clear();
		contentHolder.add(new MapItemDetailViewPresenter(mDataService, mEventBus, new MapItemDetailView()).asWidget());
	}
	
	@UiHandler("btnShowMap")
	void onBtnShowMapClick(ClickEvent event)
	{
		try
		{
			contentHolder.clear();
			mapWidget = new MapWidget();
			mapWidget.setWidth("100%");
			mapWidget.setHeight("100%");
			mapWidget.addControl(new LargeMapControl3D());
			mapWidget.setGoogleBarEnabled(true);
			// mapWidget.setStyleName(contentHolder.getStyleName());
			contentHolder.add(mapWidget);
			mapWidget.setCenter(LatLng.newInstance(50, 14.5), 7);
			mapWidget.addMapClickHandler(mClickHandler);
			mapWidget.addDomHandler(mwh, MouseWheelEvent.getType());
			
		} catch (Exception ex)
		{
			Window.alert(ex.getMessage());
		}
	}

	MouseWheelHandler mwh = new MouseWheelHandler()
	{

		@Override
		public void onMouseWheel(MouseWheelEvent event)
		{
			if (event.getDeltaY() < 0)
				mapWidget.setZoomLevel(mapWidget.getZoomLevel() + 1);
			else
				mapWidget.setZoomLevel(mapWidget.getZoomLevel() - 1);

		}
	};

	MapClickHandler mClickHandler = new MapClickHandler()
	{

		@Override
		public void onClick(MapClickEvent event)
		{
			
//			InfoWindow iw = mapWidget.getInfoWindow();
//			InfoWindowContent iwc = new InfoWindowContent(new MapItemDetailViewPresenter(mDataService, mEventBus, new MapItemDetailView()).asWidget());
//			iwc.setNoCloseOnClick(true);			
//			iw.open(event.getLatLng(), iwc);	
			
		}
	};
	
	private void showIcon(MapClickHandler.MapClickEvent event)
	{
		MarkerOptions mo = MarkerOptions.newInstance(Icon.newInstance("Icons/home.png"));
		Marker m = new Marker(event.getLatLng(), mo);
		mo.setBouncy(true);
		mo.setDraggable(true);
		mo.setTitle("Hovno");
		mo.setClickable(true);
		m.addMarkerClickHandler(mch);
		mapWidget.addOverlay(m);
	}

	MarkerClickHandler mch = new MarkerClickHandler()
	{

		@Override
		public void onClick(MarkerClickEvent event)
		{
			Marker m = (Marker) event.getSource();
			Window.alert(m.getLatLng().toUrlValue());
		}
	};
}
