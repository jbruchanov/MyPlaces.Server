package com.scurab.web.drifmaps.client.presenter;

import java.util.Date;

import javax.management.Notification;

import com.capsula.gwt.reversegeocoder.client.ExtendedPlacemark;
import com.capsula.gwt.reversegeocoder.client.ReverseGeocoder;
import com.capsula.gwt.reversegeocoder.client.ReverseGeocoderCallback;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl3D;
import com.google.gwt.maps.client.control.MapTypeControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.StreetviewOverlayChangedHandler;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.StreetviewOverlay;
import com.google.gwt.maps.client.streetview.StreetviewClient;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaOptions;
import com.google.gwt.maps.client.streetview.StreetviewPanoramaWidget;
import com.google.gwt.thirdparty.guava.common.collect.MapMaker;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.drifmaps.client.DrifMaps;
import com.scurab.web.drifmaps.client.dialog.CloseableDialog;
import com.scurab.web.drifmaps.client.dialog.InputDialog;
import com.scurab.web.drifmaps.client.dialog.NotificationDialog;
import com.scurab.web.drifmaps.client.dialog.NotificationDialog.NotificationType;
import com.scurab.web.drifmaps.client.dialog.QuestionDialog;
import com.scurab.web.drifmaps.client.form.MapItemDetailForm;
import com.scurab.web.drifmaps.client.formmodel.MapItemDetailFormModel;
import com.scurab.web.drifmaps.client.view.Menu;
import com.scurab.web.drifmaps.client.widget.StreetViewWidget;
import com.scurab.web.drifmaps.client.widget.StreetViewWidget.OnChange;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;

public class MainViewPresenter
{
	public interface Display
	{
		RootPanel getWorkSpace();
		RootPanel getMenu();
		RootPanel getTop();
	}
	
	private Display mDisplay = null;
	private MapWidget mapWidget = null;
	private Menu mMenu = null;
	private State mState = State.Default;	
	private MapItemDetailFormModel mItemModel = null;
	private Marker mCurrentAddingMarker = null;
	private StreetViewWidget mStreetView = null;
	
	private enum State
	{
		Adding,
		Default
	}
	
	public MainViewPresenter(Display display)
	{
		mDisplay = display;
		bind();
		bindMenu();
	}
	
	private void bind()
	{
		mDisplay.getWorkSpace().add(getMap());
		mDisplay.getTop().insert(getTestButton(), 0);
		
		mMenu = new Menu();
		mDisplay.getMenu().add(mMenu);
		mMenu.getAddButton().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				onChangeState();
			}
		});
		mMenu.getSaveButton().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				mMenu.getSaveButton().setEnabled(false);
			}
		});
		mMenu.getSaveButton().setEnabled(false);
			
	}
	
	private void bindMenu()
	{
		VerticalPanel panel = new VerticalPanel();		
		mItemModel = new MapItemDetailFormModel();
	    panel.add(new MapItemDetailForm(mItemModel));
	    mStreetView = new StreetViewWidget(LatLng.newInstance(50, 14.5));
	    mStreetView.setViewportMap(mapWidget);
	    mStreetView.setChangeListener(new OnChange()
		{
			@Override
			public void onChange(LatLng latlng)
			{
				if(mCurrentAddingMarker != null)
					mCurrentAddingMarker.setLatLng(latlng);
			}
		});
	    panel.add(mStreetView);
		TabPanel tp = new TabPanel();
		tp.setWidth("350px");
		tp.add(panel,"Main");		
		tp.selectTab(0);
		tp.add(new VerticalPanel(),"Context");
		mMenu.getMenuContent().add(tp);
	}
	
	public void onChangeState()
	{
		if(mState == State.Default)
			onAddingItem();
		else
			onCancelAdding();
	}
	
	public void onAddingItem()
	{
		mState = State.Adding;
		mMenu.getMenuContent().setOpen(true);
		mMenu.getAddButton().setStyleName("button cancel");
		mMenu.getAddButton().setText(DrifMaps.Words.Cancel());	
		changeCursor(Cursor.CROSSHAIR.toString());
		mItemModel.setValue(new MapItem());
		mMenu.getSaveButton().setEnabled(true);
	}
	
	public void onCancelAdding()
	{
		mState = State.Default;
		mMenu.getMenuContent().setOpen(false);
		mMenu.getAddButton().setStyleName("button add");
		mMenu.getAddButton().setText(DrifMaps.Words.Add());
		changeCursor("url(http://maps.gstatic.com/intl/en_ALL/mapfiles/openhand_8_8.cur), default");
		if(mCurrentAddingMarker != null)
			mapWidget.removeOverlay(mCurrentAddingMarker);
		mCurrentAddingMarker = null;
		mStreetView.hide();
		mMenu.getSaveButton().setEnabled(false);
	}
	
	public void onSavingItem()
	{
		mMenu.getSaveButton().setEnabled(false);
	}
	
	/**
	 * Changes default cursor on map
	 * Overrides style! 
	 * @param cursor
	 */
	private void changeCursor(String cursor)
	{
		
		NodeList<Element> elems = mapWidget.getElement().getElementsByTagName("img");
		int cnt = elems.getLength();
		for(int i = 0;i<cnt;i++)
		{
			Element e = elems.getItem(i);
			DOM.setStyleAttribute((com.google.gwt.user.client.Element) e, "cursor", cursor);
		}
	}
	
	private Button getTestButton()
	{
		Button b = new Button("TEST");
		b.setStyleName("button add");
		b.addClickHandler(new ClickHandler()
		{
			int i = 0;
			@Override
			public void onClick(ClickEvent event)
			{
				QuestionDialog.show("Question", new QuestionDialog.OnQuestionDialogButtonClick()
				{
					@Override
					public void onYesClick()
					{
						Window.alert("Yes");
					}
					
					@Override
					public void onNoClick()
					{
						Window.alert("No");
					}
					
					@Override
					public void onCancelClick()
					{
						Window.alert("cancel");
					}
				});
			}
		});
		return b;
	}
	
	private Widget getMap()
	{
		mapWidget = new MapWidget();
		mapWidget.setWidth("100%");
		mapWidget.setHeight("100%");
//		mapWidget.addControl(new LargeMapControl3D());
//		mapWidget.addControl(new MapTypeControl());
		mapWidget.setCenter(LatLng.newInstance(50, 14.5), 7);
		
		mapWidget.setContinuousZoom(true);
		MapUIOptions maui = mapWidget.getDefaultUI();
		maui.setScrollwheel(true);
		maui.setDoubleClick(false);
		maui.setLargeMapControl3d(true);
		mapWidget.setUI(maui);
		
		mapWidget.addMapClickHandler(mClickHandler);
//		mapWidget.addDomHandler(mouseWheelHandler, MouseWheelEvent.getType());
		
		return mapWidget;
	}
	
	private MapClickHandler mClickHandler = new MapClickHandler()
	{
		@Override
		public void onClick(MapClickEvent event)
		{
			if(mState == State.Adding)
			{
				LatLng e = event.getLatLng();
				showMapMarker(e);
				mItemModel.getX().setValue(e.getLatitude());
				mItemModel.getY().setValue(e.getLongitude());
				mStreetView.setLocation(e);
				handleChangePosition(e);
			}
		}
	};
	
	private void handleChangePosition(LatLng latlng)
	{
		ReverseGeocoder.reverseGeocode(latlng, new ReverseGeocoderCallback()
		{
			@Override
			public void onSuccess(ExtendedPlacemark placemark)
			{
				try
				{
					mItemModel.getCity().setValue(placemark.getCity());
					mItemModel.getStreet().setValue(placemark.getStreet());
					mItemModel.getCountry().setValue(placemark.getCountry());
				}
				catch(Exception e)
				{/*just ignor error and let user write it */}
			}
			
			@Override
			public void onFailure(LatLng point){}
		});
	}
	
	private void showMapMarker(LatLng ll)
	{
		if(mCurrentAddingMarker == null)
		{
			mCurrentAddingMarker = new Marker(ll);
			mapWidget.addOverlay(mCurrentAddingMarker);
		}
		else
		{
			mCurrentAddingMarker.setLatLng(ll);
		}
	}
	
}
