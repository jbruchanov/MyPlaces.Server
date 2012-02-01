package com.scurab.web.drifmaps.client.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.event.MapAddOverlayHandler;
import com.google.gwt.maps.client.event.MapRemoveOverlayHandler;
import com.google.gwt.maps.client.event.MapAddOverlayHandler.MapAddOverlayEvent;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.event.MapRemoveOverlayHandler.MapRemoveOverlayEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.scurab.web.drifmaps.client.DataServiceAsync;
import com.scurab.web.drifmaps.client.MockDataServiceAsync;
import com.scurab.web.drifmaps.client.component.MapController;
import com.scurab.web.drifmaps.client.map.MapItemOverlay;
import com.scurab.web.drifmaps.client.presenter.MainViewPresenter.Display;
import com.scurab.web.drifmaps.client.presenter.MainViewPresenter.State;
import com.scurab.web.drifmaps.client.view.MainView;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;
import com.scurab.web.drifmaps.shared.exception.ValidationException;
import com.scurab.web.drifmaps.shared.utils.RandomGenerator;

public class MainViewPresenterAndControllerTest extends GWTTestCase
{

	MapWidget map;
	MockDataServiceAsync service;
	List<MapAddOverlayEvent> mapAddOverlayEvents = new ArrayList<MapAddOverlayEvent>();
	List<MapRemoveOverlayEvent> mapRemoveOverlayEvents = new ArrayList<MapRemoveOverlayEvent>();
	
	@Override
	public String getModuleName()
	{
		return "com.scurab.web.drifmaps.DrifMaps";

	}

	@Override
	protected void gwtSetUp() throws Exception
	{
		super.gwtSetUp();
		MainView.initForTest();
	}

	@Test
	public void testAll()
	{
		Maps.loadMapsApi("ABQIAAAAZ3LzS5n2kDiopvOBUxlJwxSBtpmqjM9_4xe7GwB5qdGzFHEeZhQjbZf1kWCfUIvrO9rMtDj_dx87mg", "2", false,
				new Runnable()
				{
					@Override
					public void run()
					{
						map = new MapWidget();
						map.addMapAddOverlayHandler(new MapAddOverlayHandler()
						{
							@Override
							public void onAddOverlay(MapAddOverlayEvent event)
							{
								mapAddOverlayEvents.add(event);
							}
						});
						map.addMapRemoveOverlayHandler(new MapRemoveOverlayHandler()
						{
							@Override
							public void onRemoveOverlay(MapRemoveOverlayEvent event)
							{
								mapRemoveOverlayEvents.add(event);
							}
						});
						service = new MockDataServiceAsync();
						
						try
						{
							tstOnAddingNewMapItem();
							tstAddAndEditNewItem();
						}
						catch (Exception e)
						{
							fail(e.getMessage());
						}
						finishTest();
					}
				});
		delayTestFinish(50000);
	}
	
	MapController mapControllerToPresenter = null;
	
	private void tstOnAddingNewMapItem() throws ValidationException
	{
		MapControllerT mapControllerT = new MapControllerT(map, service);
		mapControllerToPresenter = mapControllerT;
		map.clearOverlays();
		mapAddOverlayEvents.clear();
		mapRemoveOverlayEvents.clear();
		
		
		
		Display d = new MainView(service);
		MainViewPresenterT mvp = new MainViewPresenterT(d, service);
		
		assertNull(d.getDataModel().getValue());
		assertEquals(0,mapAddOverlayEvents.size());
		mvp.onAddingItem();//add click
		assertEquals(mapControllerT.getState(),State.Adding);
		assertEquals(0,mapAddOverlayEvents.size());
		assertNotNull(d.getDataModel().getValue());
		
		for(int i = 0;i<10;i++)
		{
			LatLng ll1 = getRandomLatLng();
			MapClickEvent mce = new MapClickEvent(map, null, ll1, null);
			mvp.onMapClick(mce);
			mapControllerT.onMapClick(mce);			
			assertEquals(d.getDataModel().getX().getValue(),ll1.getLongitude());
			assertEquals(d.getDataModel().getY().getValue(),ll1.getLatitude());
			assertNotNull(mapControllerT.getCurrentMarker());
			assertEquals(1,mapAddOverlayEvents.size());
		}
		MapItem mi = RandomGenerator.genMapItem(false);
		d.getDataModel().setValue(mi);
		mvp.onSavingItem();
		assertTrue(mvp.calledOnSavedItem);
		mvp.calledOnSavedItem = false;
		assertEquals(2,mapAddOverlayEvents.size()); //added new icon
		assertEquals(1,mapRemoveOverlayEvents.size()); //removed old icon
		assertEquals(mapControllerT.getState(),State.Default);
		assertNull(mapControllerT.getCurrentMarker());
		assertNull(mapControllerT.getStartEditLatLng());
		assertNull(d.getDataModel().getValue());
		assertEquals(1, mapControllerT.getCurrentVisibleMapItems().size());
	}
	
	
	/**
	 * Checks error if user added new item and tried to edit it, after save maps shows another icon
	 * @throws ValidationException
	 */
	private void tstAddAndEditNewItem() throws ValidationException
	{
		mapControllerToPresenter = new MapController(map, service);
		map.clearOverlays();
		mapAddOverlayEvents.clear();
		mapRemoveOverlayEvents.clear();
		
		Display d = new MainView(service);
		MainViewPresenterT mvp = new MainViewPresenterT(d, service);
		mvp.onAddingItem();//add click
		
		assertEquals(0,mapAddOverlayEvents.size());
		LatLng ll1 = getRandomLatLng();
		MapClickEvent mce = new MapClickEvent(map, null, ll1, null);
		mvp.onMapClick(mce);
		mapControllerToPresenter.onMapClick(mce);
		assertEquals(1,mapAddOverlayEvents.size());
		
		d.getForm().getName().setValue("Name",true);
		d.getForm().getStreet().setValue("Street",true);
		d.getForm().getMapItemType().setValue("Type 01",true);
		
		mvp.onSavingItem();
		assertEquals(2,mapAddOverlayEvents.size()); //add new mapitem overlay
		assertEquals(1,mapRemoveOverlayEvents.size()); //remove default editing icon
		
		MapItemOverlay<MapItem> mio = (MapItemOverlay<MapItem>) mapAddOverlayEvents.get(1).getOverlay();
		mvp.onStartEditing(mio.getMapItem());
		mapControllerToPresenter.onMarkerClick(mio);
	
		LatLng ll2 = getRandomLatLng();
		MapClickEvent mc2 = new MapClickEvent(map, null, ll2, null);
		mvp.onMapClick(mc2);//click to change position
		mapControllerToPresenter.onMapClick(mc2);
		d.getForm().getName().setValue("Name",true);
		d.getForm().getStreet().setValue("Street",true);
		d.getForm().getMapItemType().setValue("Type 01",true);
		
		//counts should be same
		assertEquals(2,mapAddOverlayEvents.size()); 
		assertEquals(1,mapRemoveOverlayEvents.size()); 
		
		mvp.onSavingItem();
		
		//counts should be same again, (this was some error)
		assertEquals(2,mapAddOverlayEvents.size()); 
		assertEquals(1,mapRemoveOverlayEvents.size());
	}
		
	private LatLng getRandomLatLng()
	{
		return LatLng.newInstance(RandomGenerator.genRandomY(), RandomGenerator.genRandomX());
	}
	
	private class MainViewPresenterT extends MainViewPresenter
	{
		public boolean calledOnSavedItem = false;
		public MainViewPresenterT(Display display, DataServiceAsync ds)
		{
			super(display, ds);
		}
		
		@Override
		protected MapController createMapController(DataServiceAsync ds)
		{
			return mapControllerToPresenter;
		}
		@Override
		public void onSavedItem(MapItem item)
		{
			calledOnSavedItem = true;
			super.onSavedItem(item);
		}
	}
	
	public class MapControllerT extends MapController
	{
		public String currentCursor = null;
		public boolean calledOnLoad = false;
		public MapControllerT(MapWidget map, DataServiceAsync ds)
		{
			super(MainViewPresenterAndControllerTest.this.map, ds);
		}
		
		@Override
		public void onChangeCursor(String cursor)
		{
			currentCursor = cursor;
			super.onChangeCursor(cursor);
		}
		
		@Override
		protected void onLoad(List<MapItem> result)
		{
			calledOnLoad = true;
			super.onLoad(result);
		}
		
		public Marker getCurrentMarker()
		{
			return mCurrentMarker;
		}

		public LatLng getStartEditLatLng()
		{
			return mStartEditLatLng;
		}

		public HashMap<Long, MapItemOverlay<MapItem>> getCurrentVisibleMapItems()
		{
			return mCurrentVisibleMapItems;
		}

		public State getState()
		{
			return mState;
		}
	}

}
