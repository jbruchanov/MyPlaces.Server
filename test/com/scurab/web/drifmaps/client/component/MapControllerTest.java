package com.scurab.web.drifmaps.client.component;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.event.MapAddOverlayHandler;
import com.google.gwt.maps.client.event.MapRemoveOverlayHandler;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.event.MapAddOverlayHandler.MapAddOverlayEvent;
import com.google.gwt.maps.client.event.MapRemoveOverlayHandler.MapRemoveOverlayEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.LatLngBounds;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.scurab.web.drifmaps.client.DataServiceAsync;
import com.scurab.web.drifmaps.client.MockDataServiceAsync;
import com.scurab.web.drifmaps.client.map.MapItemOverlay;
import com.scurab.web.drifmaps.client.presenter.MainViewPresenter.State;
import com.scurab.web.drifmaps.client.view.MainView;
import com.scurab.web.drifmaps.shared.DataComparator;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;
import com.scurab.web.drifmaps.shared.utils.RandomGenerator;

public class MapControllerTest extends GWTTestCase
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
				
				tstClickOnMapAdding();
				tstClickOnMapAddingMoving();
				tstClickOnMapEditingAndCancel();
				tstClickOnMapEditingAndFinish();
				tstLoadData();
				finishTest();
			}
		});
		delayTestFinish(50000);
	}
	
	/**
	 * Just simple test and check states what happen if new icon is added to map
	 */
	private void tstClickOnMapAdding()
	{
		map.clearOverlays();
		mapAddOverlayEvents.clear();
		mapRemoveOverlayEvents.clear();
		
		//test for click in default state => should happen nothing
		MapControllerT mc = new MapControllerT(map, service);
		assertEquals(0,mapAddOverlayEvents.size());
		LatLng ll1 = getRandomLatLng();
		mc.onMapClick(new MapClickEvent(map, null, ll1, null));
		assertEquals(0,mapAddOverlayEvents.size());
		
		
		assertNull(mc.mCurrentMarker);
		assertNull(mc.mStartEditLatLng);
		assertEquals(mc.mState,State.Default);
		mc.startAdding();
		assertEquals(mc.mState,State.Adding);
		assertNull(mc.mCurrentMarker);
		assertNull(mc.mStartEditLatLng);
		assertEquals(mc.currentCursor, Cursor.CROSSHAIR.toString());
		
		//simulate click
		mc.onMapClick(new MapClickEvent(map, null, ll1, null));
		assertEquals(1,mapAddOverlayEvents.size());
		Marker mm = (Marker) mapAddOverlayEvents.get(0).getOverlay();
		DataComparator.assertEquals(ll1,mm.getLatLng());
		assertEquals(mc.mCurrentMarker,mm);
		
		//cancel adding
		assertEquals(0,mapRemoveOverlayEvents.size());
		mc.finishWorking(true);
		assertEquals(1,mapRemoveOverlayEvents.size());
		assertEquals(mc.mState,State.Default);
		assertNull(mc.mCurrentMarker);
		assertNull(mc.mStartEditLatLng);
		assertNull(mc.currentCursor);
		
		LatLng ll2 = getRandomLatLng();
		mc.onMapClick(new MapClickEvent(map, null, ll1, null));
		assertEquals(1, mapAddOverlayEvents.size());
	}
	
	/**
	 * Test click on maps during adding state to move adding icon
	 */
	private void tstClickOnMapAddingMoving()
	{
		map.clearOverlays();
		mapAddOverlayEvents.clear();
		mapRemoveOverlayEvents.clear();
		
		MapControllerT mc = new MapControllerT(map, service);
		mc.startAdding();
		
		LatLng ll1 = getRandomLatLng();
		mc.onMapClick(new MapClickEvent(map, null, ll1, null));
		assertEquals(1,mapAddOverlayEvents.size());
		Marker marker = (Marker) mapAddOverlayEvents.get(0).getOverlay();
		DataComparator.assertEquals(ll1,marker.getLatLng());
		DataComparator.assertEquals(ll1,mc.mCurrentMarker.getLatLng());
		assertNull(mc.mStartEditLatLng);
		
		LatLng ll2 = getRandomLatLng();
		mc.onMapClick(new MapClickEvent(map, null, ll2, null));
		assertEquals(1,mapAddOverlayEvents.size());
		DataComparator.assertEquals(ll2,mc.mCurrentMarker.getLatLng());
		DataComparator.assertEquals(ll2,marker.getLatLng());
		
		for(int i = 0;i< 10;i++)
		{
			LatLng ll = getRandomLatLng();
			mc.onMapClick(new MapClickEvent(map, null, ll, null));
			assertEquals(1,mapAddOverlayEvents.size());
			DataComparator.assertEquals(ll,mc.mCurrentMarker.getLatLng());
			DataComparator.assertEquals(ll,marker.getLatLng());
		}
		
		mc.finishWorking(true);
		assertEquals(1,mapRemoveOverlayEvents.size());
		assertNull(mc.mCurrentMarker);
	}
	
	/**
	 * Test when user add new icon, after that repeats click on icon and chaotic click on map to move editing icon
	 * At the end is canceled finish => icon should be at same place at the start
	 */
	private void tstClickOnMapEditingAndCancel()
	{
		//simple add and finish adding of new item
		map.clearOverlays();
		mapAddOverlayEvents.clear();
		mapRemoveOverlayEvents.clear();
	
		MapControllerT mc = new MapControllerT(map, service);
		mc.startAdding();		
		LatLng ll1 = getRandomLatLng();
		mc.onMapClick(new MapClickEvent(map, null, ll1, null));
		assertEquals(1,mapAddOverlayEvents.size());
		mc.finishWorking(false);
		assertEquals(1, mapRemoveOverlayEvents.size());
		
		//now presenter is calling web service to save and after succ. save is called onAddItem
		MapItem mapItem = RandomGenerator.genMapItem(false);
		LatLng mapItemLatLng = LatLng.newInstance(mapItem.getY(), mapItem.getX());
		assertEquals(0,mc.mCurrentVisibleMapItems.size());
		mc.addMapItem(mapItem);
		assertEquals(2,mapAddOverlayEvents.size());
		assertEquals(1,mc.mCurrentVisibleMapItems.size());
		
		MapItemOverlay<MapItem> mi = (MapItemOverlay<MapItem>) mapAddOverlayEvents.get(1).getOverlay();
		DataComparator.assertEquals(mapItemLatLng, mi.getLatLng());
		
		//just hoax click :) should not change anything
		mc.onMapClick(new MapClickEvent(map, null, ll1, null));		
		assertEquals(2,mapAddOverlayEvents.size());
		assertEquals(1,mc.mCurrentVisibleMapItems.size());
		assertEquals(1, mapRemoveOverlayEvents.size());
		
		assertEquals(mc.mState,State.Default);
		assertNull(mc.mCurrentMarker);
		assertNull(mc.mStartEditLatLng);
		
		//simulate editing click
		mc.onMarkerClick(mi);
		
		assertEquals(mc.mState,State.Editing);
		DataComparator.assertEquals(mapItemLatLng,mc.mCurrentMarker.getLatLng());
		DataComparator.assertEquals(mapItemLatLng,mc.mStartEditLatLng);
		assertEquals(Cursor.CROSSHAIR.toString(),mc.currentCursor);
		
		for(int i = 0;i<10;i++)//just chaotic click, icon should moves
		{
			LatLng ll = getRandomLatLng();
			mc.onMapClick(new MapClickEvent(map, null, ll, null));		
			assertEquals(1,mc.mCurrentVisibleMapItems.size());
			assertEquals(2,mapAddOverlayEvents.size());
			assertEquals(1, mapRemoveOverlayEvents.size());
			DataComparator.assertEquals(ll,mc.mCurrentMarker.getLatLng());
			DataComparator.assertEquals(mapItemLatLng,mc.mStartEditLatLng);
		}
		
		mc.finishWorking(true);//canceled
		//after finish nothing should happen
		assertEquals(1,mc.mCurrentVisibleMapItems.size());
		assertEquals(2,mapAddOverlayEvents.size());
		assertEquals(1, mapRemoveOverlayEvents.size());
		//only editing icon should be on start place
		DataComparator.assertEquals(mapItemLatLng, mi.getLatLng());
	}
	
	/**
	 * Test when user add new icon, after that repeats click on icon and chaotic click on map to move editing icon
	 * At the end is succ finish => icon should be at the last click position
	 */
	private void tstClickOnMapEditingAndFinish()
	{
		//simple add and finish adding of new item
		map.clearOverlays();
		mapAddOverlayEvents.clear();
		mapRemoveOverlayEvents.clear();
		MapControllerT mc = new MapControllerT(map, service);
		mc.startAdding();		
		LatLng ll1 = getRandomLatLng();
		mc.onMapClick(new MapClickEvent(map, null, ll1, null));
		mc.finishWorking(false);
		
		//now presenter is calling web service to save and after succ. save is called onAddItem
		MapItem mapItem = RandomGenerator.genMapItem(false);
		LatLng mapItemLatLng = LatLng.newInstance(mapItem.getY(), mapItem.getX());
		mc.addMapItem(mapItem);
		MapItemOverlay<MapItem> mi = (MapItemOverlay<MapItem>) mapAddOverlayEvents.get(1).getOverlay();
		DataComparator.assertEquals(mapItemLatLng, mi.getLatLng());
		
		//simulate click on icon
		mc.startEditing(mi);
		
		LatLng ll = null;
		for(int i = 0;i<10;i++)//just chaotic click, icon should moves
		{
			ll = getRandomLatLng();
			mc.onMapClick(new MapClickEvent(map, null, ll, null));		
			DataComparator.assertEquals(ll,mi.getLatLng());
			DataComparator.assertEquals(mapItemLatLng,mc.mStartEditLatLng);
		}
		
		mc.finishWorking(false);//finished => not canceled
		assertEquals(mc.mState,State.Default);
		assertNull(mc.mCurrentMarker);
		assertNull(mc.mStartEditLatLng);
		//after finish nothing should happen
		assertEquals(1,mc.mCurrentVisibleMapItems.size());
		assertEquals(2,mapAddOverlayEvents.size());
		assertEquals(1, mapRemoveOverlayEvents.size());
		//only editing icon should be at the last click position
		DataComparator.assertEquals(ll, mi.getLatLng());
	}
	
	/**
	 * Tests loading data from data service 
	 * Called on drag map end event
	 */
	List<MapItem> randomData = null;
	private void tstLoadData()
	{
		//simple add and finish adding of new item
		map.clearOverlays();
		mapAddOverlayEvents.clear();
		mapRemoveOverlayEvents.clear();
		
		MapControllerT mc = new MapControllerT(map, service);		
		randomData = new ArrayList<MapItem>();
		int iterator = 0;
		for(;iterator<20;iterator++)
		{
			MapItem mi = RandomGenerator.genMapItem(false);
			mi.setId(iterator); //init ids 
			randomData.add(mi);
		}
		
		
		service.setOnGetListener(new MockDataServiceAsync.OnGetListener()
		{
			@Override public void get(String className, String filters, boolean deep, AsyncCallback<List<?>> callback){}

			@Override
			public void get(String className, double x1, double x2, double y1, double y2, boolean deep, AsyncCallback<List<?>> callback)
			{				
				callback.onSuccess(randomData);
			}

			@Override
			public void get(String className, AsyncCallback<List<?>> callback){}
		});
		
		//simulate map drag move 
		LatLngBounds llb = LatLngBounds.newInstance(LatLng.newInstance(0, 0), LatLng.newInstance(80, 80));
		mc.loadIcons(12, llb); //just ensure about good zoom limit
		assertTrue(mc.calledOnLoad); //should becalled
		mc.calledOnLoad = false;
		assertEquals(randomData.size(),mc.mCurrentVisibleMapItems.size());//this icons should be here
		assertEquals(randomData.size(),mapAddOverlayEvents.size());
		
		//call again, no new icon should be added into map
		mc.loadIcons(12, llb); //
		assertTrue(mc.calledOnLoad); //should becalled
		mc.calledOnLoad = false;
		assertEquals(randomData.size(),mc.mCurrentVisibleMapItems.size());//this icons should be here
		assertEquals(randomData.size(),mapAddOverlayEvents.size());
		
		//add 10 new icons into database simulates different db result depending on latlngbounds 
		for(;iterator<30;iterator++)
		{
			MapItem mi = RandomGenerator.genMapItem(false);
			mi.setId(iterator); //init ids 
			randomData.add(mi);
		}
		
		//call again, only 10 news icon should be added into map
		mc.loadIcons(12, llb); //
		assertTrue(mc.calledOnLoad); //should becalled
		mc.calledOnLoad = false;
		assertEquals(randomData.size(),mc.mCurrentVisibleMapItems.size());//this icons should be here
		assertEquals(randomData.size(),mapAddOverlayEvents.size());
		
		for(int i = 0;i<iterator;i++)
		{
			long l = i;
			//check added data, should be same
			assertTrue(mc.mCurrentVisibleMapItems.containsKey(l));
			MapItemOverlay<MapItem> mi = (MapItemOverlay<MapItem>) mapAddOverlayEvents.get(i).getOverlay();
			DataComparator.assertEquals(randomData.get(i), mi.getMapItem(), true);
			DataComparator.assertEquals(randomData.get(i), mi.getMapItem(), true);
		}
		
	}
	private LatLng getRandomLatLng()
	{
		return LatLng.newInstance(RandomGenerator.genRandomY(), RandomGenerator.genRandomX());
	}
	
	private void assertEquals(LatLng ll1, LatLng ll2)
	{
		assertEquals(ll1.getLatitude(),ll2.getLatitude());
		assertEquals(ll1.getLongitude(),ll2.getLongitude());
	}
	
	private class MapControllerT extends MapController
	{
		public String currentCursor = null;
		public boolean calledOnLoad = false;
		public MapControllerT(MapWidget map, DataServiceAsync ds)
		{
			super(MapControllerTest.this.map, ds);
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
			assertEquals(randomData.size(), result.size());
		}
	}
}
