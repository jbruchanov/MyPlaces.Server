package com.scurab.web.drifmaps.client.map;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.scurab.web.drifmaps.shared.interfaces.HasCoordinates;
import com.scurab.web.drifmaps.shared.interfaces.HasIcon;
import com.scurab.web.drifmaps.shared.interfaces.HasId;
import com.scurab.web.drifmaps.shared.interfaces.HasTitle;

public class MapItemOverlay<T extends HasCoordinates & HasId & HasIcon & HasTitle> extends Marker
{
	private T mMapItem = null;
	
	public MapItemOverlay(T mi)
	{
		super(getLatLng(mi),getOptions(null, mi.getIconUrl(),mi.getTitle()));
		mMapItem = mi;
	}
	
	public MapItemOverlay(T mi, MarkerOptions options)
	{
		super(getLatLng(mi), getOptions(options, mi.getIconUrl(),mi.getTitle()));
		mMapItem = mi;
	}
	
	protected static LatLng getLatLng(HasCoordinates mi)
	{
		return LatLng.newInstance(mi.getY(),mi.getX()); 
	}
	
	public long getId()
	{
		return mMapItem.getId();
	}
	
	public T getMapItem()
	{
		return mMapItem;
	}
	
	public void setMapItem(T item)
	{
		mMapItem = item;
	}
	
	private static MarkerOptions getOptions(MarkerOptions options, String icoUrl, String title)
	{
		if(options == null)
			options = MarkerOptions.newInstance(); 
		Icon icon = Icon.newInstance(icoUrl);		
		icon.setIconAnchor(com.google.gwt.maps.client.geom.Point.newInstance(12, 12));
		options.setIcon(icon);
		options.setBouncy(true);
		options.setTitle(title);
		options.setClickable(true);		
		return options;
	}
}
