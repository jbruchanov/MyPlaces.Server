package com.scurab.web.drifmaps.client.component;

import java.util.HashMap;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.event.MapDragEndHandler;
import com.google.gwt.maps.client.event.MapZoomEndHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.scurab.web.drifmaps.client.AppConstants;
import com.scurab.web.drifmaps.client.DataServiceAsync;
import com.scurab.web.drifmaps.client.Settings;
import com.scurab.web.drifmaps.client.dialog.NotificationDialog;
import com.scurab.web.drifmaps.client.map.MapItemOverlay;
import com.scurab.web.drifmaps.client.presenter.MainViewPresenter.State;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;
import com.scurab.web.drifmaps.shared.datamodel.Star;

/**
 * Basic map controller
 * Handles loading icons, changin of cursor...
 * @author Joe Scurab
 *
 */
public class MapController
{
	private MapWidget map = null;
	protected Marker mCurrentMarker = null;
	protected LatLng mStartEditLatLng = null;
	protected static final String DEFAULT_CURSOR = "url(http://maps.gstatic.com/intl/en_ALL/mapfiles/openhand_8_8.cur), default";
	private DataServiceAsync mDataService = null;
	protected HashMap<Long, MapItemOverlay<MapItem>> mCurrentVisibleMapItems = new HashMap<Long,MapItemOverlay<MapItem>>();
	protected State mState = State.Default;
	private OnMapMarkerClick listener = null;
	
	public interface OnMapMarkerClick
	{
		void onMapItemClick(MapItemOverlay<MapItem> item);
		void onStarClick(MapItemOverlay<Star> item);
	}
	
	public void setOnMapMarkerClick(OnMapMarkerClick listener)
	{
		this.listener = listener;
	}
	
	public MapController(MapWidget map, DataServiceAsync ds)
	{
		this.map = map;
		mDataService = ds;
		bind();
	}
	
	public void addMapItem(MapItem item)
	{
		MapItemOverlay<MapItem> mio = new MapItemOverlay<MapItem>(item);
		mCurrentVisibleMapItems.put(item.getId(),mio);
		map.addOverlay(mio);
		mio.addMarkerClickHandler(new MarkerClickHandler()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(MarkerClickEvent event)
			{
				MapItemOverlay<MapItem> mi = (MapItemOverlay<MapItem>)event.getSource(); 
				onMarkerClick(mi);
				if(listener != null)
					listener.onMapItemClick(mi);
			}
		});		
	}
	
	public void startAdding()
	{
		mState = State.Adding;		
		onChangeCursor(Cursor.CROSSHAIR.toString());
	}
	
	public void startEditing(MapItemOverlay<MapItem> mio)
	{
		mState = State.Editing;
		mCurrentMarker = mio;
		LatLng ll = mio.getLatLng();
		mStartEditLatLng = LatLng.newInstance(ll.getLatitude(), ll.getLongitude());
		onChangeCursor(Cursor.CROSSHAIR.toString());
	}
	
	public void finishWorking(boolean canceled)
	{
		switch(mState)
		{
			case Adding:
				hideMapMarker();
				break;
			case Editing:
				if(canceled)
					mCurrentMarker.setLatLng(mStartEditLatLng);
				mCurrentMarker = null;
				mStartEditLatLng = null;
				break;
		}
		mState = State.Default;
		onChangeCursor(null);
	}

	private void bind()
	{
		map.setWidth("100%");
		map.setHeight("100%");
//		mapWidget.addControl(new LargeMapControl3D());
//		mapWidget.addControl(new MapTypeControl());
		map.setCenter(
				LatLng.newInstance(AppConstants.Settings.DEFAULT_MAP_LOCATION_Y,AppConstants.Settings.DEFAULT_MAP_LOCATION_X),
				AppConstants.Settings.DEFAULT_MAP_ZOOM);
		
		map.setContinuousZoom(true);
		MapUIOptions maui = map.getDefaultUI();
		maui.setScrollwheel(true);
		maui.setDoubleClick(false);
		maui.setLargeMapControl3d(true);
		map.setUI(maui);
		
		map.addMapDragEndHandler(mDragEndHandler);
		map.addMapZoomEndHandler(mZoomEventHandler);
		map.addMapClickHandler(mClickHandler);	
		
	}
	
	public void loadIcons(int zoom, LatLngBounds bounds)
	{
		if(zoom < Settings.MIN_ZOOM_TO_LOAD_ICONS)
			return;
		double x1 = bounds.getSouthWest().getLongitude();
		double x2 = bounds.getNorthEast().getLongitude();
		double y1 = bounds.getSouthWest().getLatitude();
		double y2 = bounds.getNorthEast().getLatitude();
		mDataService.get(MapItem.class.getName(), x1, y1, x2, y2, false, new AsyncCallback<List<?>>()
		{
			@Override
			public void onFailure(Throwable caught)
			{
				NotificationDialog.show(caught);
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(List<?> result)
			{
				onLoad((List<MapItem>) result);
			}
		});
	}
	
	protected void onLoad(List<MapItem> result)
	{
		if(result.size() == 0)
			return;
		List<MapItem> val = result;
		for(MapItem item : val)
		{
			MapItemOverlay<MapItem> mio = mCurrentVisibleMapItems.get(item.getId()); 
			if(mio == null)
				addMapItem(item);
			else
				mio.setMapItem(item);
		}
	}
	
	private MapDragEndHandler mDragEndHandler = new MapDragEndHandler()
	{
		@Override
		public void onDragEnd(MapDragEndEvent event)
		{
			MapWidget m = event.getSender();
			loadIcons(m.getZoomLevel(), m.getBounds());
		}
	};
	private MapZoomEndHandler mZoomEventHandler = new MapZoomEndHandler()
	{
		@Override
		public void onZoomEnd(MapZoomEndEvent event)
		{
			MapWidget m = event.getSender();
			loadIcons(m.getZoomLevel(), m.getBounds());	
		}
	};
	
	/**
	 * Shows map marker on position
	 * If map marker already exists it will translate it
	 * Must be visible to change values from streetview
	 * @param ll
	 */
	public void moveCurrentMapMarker(LatLng ll)
	{
		if(mCurrentMarker != null)
			mCurrentMarker.setLatLng(ll);
	}
	
	public void onMarkerClick(MapItemOverlay<MapItem> mapItemOverlay)
	{
//		InfoWindow iw = map.getInfoWindow();
//		InfoWindowContent iwc = new InfoWindowContent(mapItemOverlay.getMapItem().getName());
//		iw.open(mapItemOverlay.getLatLng(), iwc);
		startEditing(mapItemOverlay);
	}
	
	private void hideMapMarker()
	{
		if(mCurrentMarker != null)
		{
			map.removeOverlay(mCurrentMarker);
			mCurrentMarker = null;
		}
	}
	
	/**
	 * Changes default cursor on map
	 * Overrides style! 
	 * @param cursor
	 */
	protected void onChangeCursor(String cursor)
	{
		if(cursor == null)
			cursor = DEFAULT_CURSOR;
		NodeList<Element> elems = map.getElement().getElementsByTagName("img");
		int cnt = elems.getLength();
		for(int i = 0;i<cnt;i++)
		{
			Element e = elems.getItem(i);
			DOM.setStyleAttribute((com.google.gwt.user.client.Element) e, "cursor", cursor);
		}
	}
	
	public void onMapClick(MapClickEvent event)
	{
		LatLng e = event.getLatLng();
		if(e == null)
			e = event.getOverlayLatLng();

		switch(mState)
		{
			case Adding:
				if(mCurrentMarker == null)
				{
					mCurrentMarker = new Marker(e);
					map.addOverlay(mCurrentMarker);
				}	
				else
					moveCurrentMapMarker(e);
				break;
			case Editing:
				moveCurrentMapMarker(e);
				break;
		}
	}
	
	private MapClickHandler mClickHandler = new MapClickHandler()
	{
		@Override
		public void onClick(MapClickEvent event)
		{
			onMapClick(event);
		}
		
		
	};
	
	
}