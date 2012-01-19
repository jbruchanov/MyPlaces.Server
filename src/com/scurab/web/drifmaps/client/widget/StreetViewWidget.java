package com.scurab.web.drifmaps.client.widget;

import com.google.gwt.maps.client.MapType;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.StreetviewErrorHandler;
import com.google.gwt.maps.client.event.StreetviewInitializedHandler;
import com.google.gwt.maps.client.event.StreetviewPitchChangedHandler;
import com.google.gwt.maps.client.event.StreetviewYawChangedHandler;
import com.google.gwt.maps.client.event.StreetviewZoomChangedHandler;
import com.google.gwt.maps.client.event.StreetviewErrorHandler.StreetviewErrorEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.maps.client.streetview.LatLngStreetviewCallback;
import com.google.gwt.maps.client.streetview.Pov;
import com.google.gwt.maps.client.streetview.StreetviewClient;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaOptions;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaWidget;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StreetViewWidget extends Composite
{
	public interface OnChange
	{
		void onChange(LatLng latlng);
	}
	private VerticalPanel panel;
	private MapWidget map;
	private final StreetviewPanoramaWidget panorama;
	private final StreetviewClient svClient;
	private final LatLng tenthStreet = LatLng.newInstance(33.78148, -84.38713);

	private Pov currentPov = Pov.newInstance();
	private Polygon viewPolygon;
	private OnChange mChangeListener = null;

	public StreetViewWidget(LatLng position)
	{
		this(position,false);
	}
	
	public StreetViewWidget(LatLng position, final boolean visible)
	{
		StreetviewPanoramaOptions options = StreetviewPanoramaOptions.newInstance();
		options.setLatLng(tenthStreet);
		svClient = new StreetviewClient();
		panorama = new StreetviewPanoramaWidget(options);
		panorama.setSize("320px", "300px");

		panorama.addInitializedHandler(new StreetviewInitializedHandler()
		{
			public void onInitialized(StreetviewInitializedEvent event)
			{
//				if(!visible)
//					panorama.hide();
			}
		});

		panorama.addPitchChangedHandler(new StreetviewPitchChangedHandler()
		{
			public void onPitchChanged(StreetviewPitchChangedEvent event)
			{				
				currentPov.setPitch(event.getPitch());
				updatePolyline();
			}
		});

		panorama.addYawChangedHandler(new StreetviewYawChangedHandler()
		{
			public void onYawChanged(StreetviewYawChangedEvent event)
			{				
				currentPov.setYaw(event.getYaw());
				updatePolyline();
			}
		});

		panorama.addZoomChangedHandler(new StreetviewZoomChangedHandler()
		{
			public void onZoomChanged(StreetviewZoomChangedEvent event)
			{				
				currentPov.setZoom(event.getZoom());
				updatePolyline();
			}
		});
		
		initWidget(panorama);		
	}
	
	public void show()
	{
		panorama.show();
	}
	
	public void hide()
	{
		panorama.hide();
		if (viewPolygon != null)
		{
			mapViewport.removeOverlay(viewPolygon);
			viewPolygon = null;
		}
	}
	
	/**
	 * Sets location
	 * @param point
	 */
	public void setLocation(LatLng point)
	{
//		panorama.setLocationAndPov(latLng, currentPov);	
//		Window.alert("set");
//		LatLng point = event.getLatLng() == null ? event.getOverlayLatLng() : event.getLatLng();
		if (point != null)
		{
			svClient.getNearestPanoramaLatLng(point, new LatLngStreetviewCallback()
			{
				@Override
				public void onFailure()
				{
					panorama.hide();
					if (viewPolygon != null)
					{
						mapViewport.removeOverlay(viewPolygon);
						viewPolygon = null;
					}
				}

				@Override
				public void onSuccess(LatLng point)
				{
					panorama.setLocationAndPov(point, Pov.newInstance());
					if(panorama.isHidden())
						panorama.show();
				}
			});
		}
	}
	
	private MapWidget mapViewport = null;
	public void setViewportMap(MapWidget map)
	{
		mapViewport = map;
	}

	private void updatePolyline()
	{
		LatLng currentLatLng = panorama.getLatLng();
		if(mChangeListener != null)
			mChangeListener.onChange(currentLatLng);
		if(mapViewport == null)
			return;
		
		if (viewPolygon != null)
			mapViewport.removeOverlay(viewPolygon);
		
		// Some simple math to calculate viewPolygon

		double yaw = currentPov.getYaw();
		double distanceFactor = Math.cos(Math.toRadians(currentPov.getPitch())) * 0.0015 + 0.0005;
		double zoomFactor = currentPov.getZoom() * 0.7 + 1;

		LatLng[] points = new LatLng[11];
		points[0] = points[10] = currentLatLng;
		for (int i = 1; i < 10; i++)
		{
			double angle = Math.toRadians(yaw + (i - 5) * 7d / zoomFactor);
			points[i] = LatLng.newInstance(currentLatLng.getLatitude() + Math.cos(angle) * distanceFactor, currentLatLng.getLongitude()
					+ Math.sin(angle) * distanceFactor);
		}

		viewPolygon = new Polygon(points, "blue", 1, 0.5, "blue", 0.15);
		mapViewport.addOverlay(viewPolygon);
	}

	private void tohleFacha()
	{
		StreetviewPanoramaOptions options = StreetviewPanoramaOptions.newInstance();
		options.setLatLng(LatLng.newInstance(33.78148, -84.38713));
		StreetviewPanoramaWidget panorama = new StreetviewPanoramaWidget(options);
		panorama.setSize("500px", "300px");

		MapWidget map = new MapWidget(LatLng.newInstance(33.78148, -84.38713), 16);
		map.setSize("500px", "300px");

		VerticalPanel panel = new VerticalPanel();
		panel.add(panorama);
		panel.add(map);
		// cd.add(panel);
		initWidget(panel);
	}
	
	public void setChangeListener(OnChange listener)
	{
		mChangeListener = listener; 
	}
}
