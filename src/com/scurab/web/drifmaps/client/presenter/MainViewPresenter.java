package com.scurab.web.drifmaps.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capsula.gwt.reversegeocoder.client.ExtendedPlacemark;
import com.capsula.gwt.reversegeocoder.client.ReverseGeocoder;
import com.capsula.gwt.reversegeocoder.client.ReverseGeocoderCallback;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.event.MapClickHandler.MapClickEvent;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LocationCallback;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.scurab.web.drifmaps.client.AppConstants;
import com.scurab.web.drifmaps.client.DataService;
import com.scurab.web.drifmaps.client.DataServiceAsync;
import com.scurab.web.drifmaps.client.DrifMaps;
import com.scurab.web.drifmaps.client.component.ContextItem;
import com.scurab.web.drifmaps.client.component.DetailItem;
import com.scurab.web.drifmaps.client.component.MapController;
import com.scurab.web.drifmaps.client.dialog.ContextDetailInputDialog;
import com.scurab.web.drifmaps.client.dialog.InputDialog;
import com.scurab.web.drifmaps.client.dialog.NotificationDialog;
import com.scurab.web.drifmaps.client.dialog.QuestionDialog;
import com.scurab.web.drifmaps.client.form.MapItemDetailForm;
import com.scurab.web.drifmaps.client.formmodel.MapItemDetailFormModel;
import com.scurab.web.drifmaps.client.map.MapItemOverlay;
import com.scurab.web.drifmaps.client.widget.StreetViewWidget;
import com.scurab.web.drifmaps.shared.datamodel.Detail;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;
import com.scurab.web.drifmaps.shared.datamodel.Star;
import com.scurab.web.drifmaps.shared.exception.ValidationException;

public class MainViewPresenter
{
	private Button getTestButton()
	{
		final Button b = new Button("TEST");
		b.setStyleName("gwt-Button");
		b.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				
			}
		});
		return b;
	}	
	
	public interface Display
	{
		RootPanel getWorkSpace();
		RootPanel getMenuContainer();
		RootPanel getTopContainer();
		MapItemDetailForm getForm();
		MapWidget getMapWidget();
		StreetViewWidget getStreetView();
		HasClickHandlers getPlusButton();
		HasClickHandlers getMinusButton();
		HasClickHandlers getCustomButton();
		HasWidgets getContextItemsContainer();
		MapItemDetailFormModel getDataModel();
		void setMapItem(MapItem mi);
		boolean validate();
		Button getAddButton();
		Button getSaveButton();
		DisclosurePanel getMenuContent();
		void setCurrentMenuTab(int i);
		Button getSearchButton();
		HasText getSearchBox();
	}
	
	private Display mDisplay = null;
	private MapController mMapController = null;
	private State mState = State.Default;	
	private DataServiceAsync mDataService = null;
	private MapWidget mapWidget = null;
	private MapItemDetailFormModel mDataModel = null;
	private Button mSearchButton = null;
	
	public enum State
	{
		Adding,
		Editing,
		Default
	}
	
	public MainViewPresenter(Display display, DataServiceAsync ds)
	{
		mDisplay = display;
		mapWidget = display.getMapWidget();
		mDataService = ds;
		mDataModel = mDisplay.getDataModel();		
		bind();
		bindMap(ds);
		bindMenu(ds);
	}
	
	/**
	 * Basic bind to display
	 * Init getAdd/Save Button handlers
	 * @param ds
	 */
	private void bindMap(DataServiceAsync ds)
	{
		//mDisplay.getTopContainer().insert(getTestButton(), 0);
		mMapController = createMapController(ds);
		mapWidget.addMapClickHandler(mClickHandler);	
		mMapController.setOnMapMarkerClick(new MapController.OnMapMarkerClick()
		{
			@Override
			public void onStarClick(MapItemOverlay<Star> item)
			{
				
			}
			
			@Override
			public void onMapItemClick(MapItemOverlay<MapItem> item)
			{
				handleStartEditing(item.getMapItem());
			}
		});
	}
	
	protected MapController createMapController(DataServiceAsync ds)
	{
		return new MapController(mapWidget,ds);
	}
	
	/**
	 * Bind to window
	 */
	private void bind()
	{
	    mDisplay.getStreetView().setViewportMap(mapWidget);
	    mDisplay.getSearchButton().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				String text = mDisplay.getSearchBox().getText().trim();
				if(text.length() > 0)
				{
					onSearch(text);
				}
			}
		});
	}
	    
	/**
	 * Called when search button is clicked and search query is not empty
	 * @param text
	 */
	public void onSearch(String text)
	{
		Geocoder g = new Geocoder();
		g.getLocations(text, new LocationCallback()
		{
			
			@Override
			public void onSuccess(JsArray<Placemark> locations)
			{
				if(locations.length() > 0)
				{
					Placemark marks = locations.get(0);
					onSearchAddressResult(marks);
				}
			}
			
			@Override public void onFailure(int statusCode){}
		});
	}
	
	/**
	 * Called like geocoder callback
	 * @param mark
	 */
	public void onSearchAddressResult(Placemark mark)
	{
		handleSearchAddressResult(mark);
	}
	
	private void handleSearchAddressResult(Placemark mark)
	{
		mDisplay.getMapWidget().setCenter(mark.getPoint());
		mDisplay.getMapWidget().setZoomLevel(15);
		final Marker m = new Marker(mark.getPoint());
		m.addMarkerClickHandler(new MarkerClickHandler()
		{
			@Override
			public void onClick(MarkerClickEvent event)
			{
				mDisplay.getMapWidget().removeOverlay(m);
			}
		});
		mDisplay.getMapWidget().addOverlay(m);
	}
	
	public void onStreetViewLocationChange(LatLng latlng)
	{
		mMapController.moveCurrentMapMarker(latlng);
	}
	/**
	 * Bind menu
	 * Creates TabPanel and nested objects
	 * @param ds
	 */
	private void bindMenu(DataServiceAsync ds)
	{
		
		mDisplay.getAddButton().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				if(mState == State.Default)
					onAddingItem();
				else
					onFinishAdding(true);
			}
		});
		mDisplay.getSaveButton().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				try
				{
					if (mDisplay.validate())
						onSavingItem();
				}
				catch(ValidationException ve)
				{
					//it's not neccassary to catch, its exception for testing, validate will show it
				}
			}
		});
		mDisplay.getSaveButton().setEnabled(false);
		
		mDisplay.getPlusButton().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				handleClickAddProCons(true);
			}
		});
		mDisplay.getMinusButton().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				handleClickAddProCons(false);
			}
		});
		
		mDisplay.getCustomButton().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				handleClickCustomDetail();
			}
		});
	}
	
	private void handleClickAddProCons(final boolean pro)
	{
		SafeUri uri = (pro) 
						? UriUtils.fromSafeConstant(AppConstants.BiggerIcons.ICO_PLUS)
						: UriUtils.fromSafeConstant(AppConstants.BiggerIcons.ICO_MINUS);
						
		InputDialog.show(uri, new InputDialog.OnInputDialogButtonClick()
		{
			@Override
			public void onOkClick(String value)
			{
				if(pro)
					onAddPro(value, true);
				else
					onAddCon(value, true);
			}
			
			@Override public void onCancelClick(){}
		});
	}
	
	private void handleClickCustomDetail()
	{
		ContextDetailInputDialog.show(null, null, new Date(System.currentTimeMillis()), 
				new ContextDetailInputDialog.OnInputDialogButtonClick()
		{
			@Override
			public void onOkClick(Detail d)
			{
				onAddDetail(d, true);
			}
			@Override public void onCancelClick() {}
		});
	}
	
	/**
	 * Called when is created Pros
	 * @param value for Pro
	 * @param fireEvent adding value into model
	 */
	public void onAddPro(String value, boolean fireEvent)
	{
		if(value != null && value.trim().length() != 0)
		{
			final ContextItem<String> ci = new ContextItem<String>();
			ci.getCloseButton().addClickHandler(new ClickHandler()
			{
				@Override
				public void onClick(ClickEvent event)
				{
					handleRemovePro(ci);
				}
			});	
			ci.setTextValue(value);
			ci.setIcon(ContextItem.IconType.Pros);
			mDisplay.getContextItemsContainer().add(ci);
			if(fireEvent)
			{
				if(mDataModel.getValue().getPros() == null)
					mDataModel.getValue().setPros(new ArrayList<String>());
				mDataModel.getValue().getPros().add(value);
			}
		}
	}
	
	/**
	 *  Called when is created Cons
	 * @param value
	 */
	public void onAddCon(String value, boolean fireEvent)
	{
		if(value != null && value.trim().length() != 0)
		{
			final ContextItem<String> ci = new ContextItem<String>();
			ci.getCloseButton().addClickHandler(new ClickHandler()
			{
				@Override
				public void onClick(ClickEvent event)
				{
					handleRemoveCon(ci);
				}
			});
			ci.setTextValue(value);
			ci.setIcon(ContextItem.IconType.Cons);
			mDisplay.getContextItemsContainer().add(ci);
			if(fireEvent)
			{
				if(mDataModel.getValue().getCons() == null)
					mDataModel.getValue().setCons(new ArrayList<String>());
				mDataModel.getValue().getCons().add(value);
			}
		}
	}
	
	private void handleRemovePro(final ContextItem<String> ci)
	{
		QuestionDialog.show(DrifMaps.Words.ReallyQstn(), new QuestionDialog.OnQuestionDialogButtonClick()
		{
			@Override
			public void onYesClick()
			{
				onRemovePro(ci);
			}
			@Override public void onNoClick(){}
			@Override public void onCancelClick(){}
		});
	}
	
	public void onRemovePro(final ContextItem<String> ci)
	{
		mDisplay.getContextItemsContainer().remove(ci);
		mDataModel.getValue().getPros().remove(ci.getTextValue());
	}
	
	private void handleRemoveCon(final ContextItem<String> ci)
	{
		QuestionDialog.show(DrifMaps.Words.ReallyQstn(), new QuestionDialog.OnQuestionDialogButtonClick()
		{
			@Override
			public void onYesClick()
			{
				onRemoveCon(ci);
			}
			@Override public void onNoClick(){}
			@Override public void onCancelClick(){}
		});
	}
	
	public void onRemoveCon(final ContextItem<String> ci)
	{
		mDisplay.getContextItemsContainer().remove(ci);
		mDataModel.getValue().getCons().remove(ci.getTextValue());
	}
	
	public void onAddDetail(final Detail d, boolean fireEvent)
	{
		final DetailItem<Detail> di = new DetailItem<Detail>(d);
		di.getCloseButton().addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				handleRemoveDetail(di);
			}
		});

		mDisplay.getContextItemsContainer().add(di);
		if(fireEvent)
		{
			if(mDataModel.getValue().getDetails() == null)
				mDataModel.getValue().setDetails(new ArrayList<Detail>());
			mDataModel.getValue().getDetails().add(d);
		}
	}
	
	private void handleRemoveDetail(final DetailItem<Detail> di)
	{
		QuestionDialog.show(DrifMaps.Words.ReallyQstn(), new QuestionDialog.OnQuestionDialogButtonClick()
		{
			@Override
			public void onYesClick()
			{
				onRemoveDetail(di);
			}
			@Override public void onNoClick(){}
			@Override public void onCancelClick(){}
		});
	}
	
	public void onRemoveDetail(final DetailItem<Detail> di)
	{
		mDisplay.getContextItemsContainer().remove(di);
		mDataModel.getValue().getDetails().remove(di.getValue());
	}
	
	/**
	 * Changes states of objects during @value {@link State#Adding}
	 */
	public void onAddingItem()
	{
		mState = State.Adding;
		mDisplay.getMenuContent().setOpen(true);
		mDisplay.getAddButton().setStyleName("button cancel");
		mDisplay.getAddButton().setText(DrifMaps.Words.Cancel());	
		mDisplay.setMapItem(new MapItem());
		mDisplay.getSaveButton().setEnabled(true);
		mMapController.startAdding();
	}
	
	private void handleStartEditing(final MapItem mi)
	{
		String filter = "id = " + mi.getId();
		mDataService.get(MapItem.class.getName(), filter, true, new AsyncCallback<List<?>>()
		{
			@Override
			public void onSuccess(List<?> result)
			{
				if(result.size() == 1)
				{
					onStartEditing((MapItem) result.get(0));
				}
				else
				{
					mMapController.finishWorking(true);
					NotificationDialog.show("Should return only 1 record!", NotificationDialog.NotificationType.Warning);
				}
			}
			
			@Override
			public void onFailure(Throwable caught)
			{
				NotificationDialog.show(caught);
				mMapController.finishWorking(true);
			}
		});
	}
	
	/**
	 * Event called on start editing
	 * @param mi
	 */
	public void onStartEditing(MapItem mi)
	{		
		//map handler call this method => witch handler to edit state made before this call
		mState = State.Editing;		
		mDisplay.getMenuContent().setOpen(true);
		mDisplay.getAddButton().setStyleName("button cancel");
		mDisplay.getAddButton().setText(DrifMaps.Words.Cancel());	
		mDisplay.setMapItem(mi);
		mDisplay.getSaveButton().setEnabled(true);
		if(mi.getPros() != null)
		{
			for(String s : mi.getPros())
				onAddPro(s, false);	
		}
		
		if(mi.getCons() != null)
		{
			for(String s : mi.getCons())
				onAddCon(s, false);
		}
		
		if(mi.getDetails() != null)
		{
			for(Detail d : mi.getDetails())
				onAddDetail(d, false);
		}
		
	}
	
	/**
	 * Changes states of objects during @value {@link State#Default}
	 */
	public void onFinishAdding(boolean canceled)
	{
		mState = State.Default;
		mMapController.finishWorking(canceled);
		mDisplay.getMenuContent().setOpen(false);
		onClearContextItems();
		mDisplay.setMapItem(null);
		mDisplay.getAddButton().setStyleName("button add");
		mDisplay.getAddButton().setText(DrifMaps.Words.Add());
		mDisplay.getStreetView().hide();
		mDisplay.getSaveButton().setEnabled(false);
		mDisplay.setCurrentMenuTab(0);
		mDisplay.getForm().getMapItemType().setSelectedIndex(0);
	}
	
	public void onSavingItem() throws ValidationException
	{
		if (!mDisplay.validate())
			throw new ValidationException("Map Item validation");
		
		MapItem item = mDisplay.getDataModel().getValue();
		mDisplay.getSaveButton().setEnabled(false);
		mDisplay.getSaveButton().setText(DrifMaps.Words.Saving());
		int operation = (item.getId() == 0) ? DataService.ADD : DataService.UPDATE;
		
		mDataService.processMapItem(item, operation, new AsyncCallback<MapItem>()
		{
			@Override
			public void onSuccess(MapItem result)
			{
				onSavedItem(result);
			}

			@Override
			public void onFailure(Throwable caught)
			{
				NotificationDialog.show(caught);
			}
		});
	}
	
	/**
	 * Called after succesfull save
	 * @param item
	 */
	public void onSavedItem(MapItem item)
	{
		if(mState == State.Adding)
			mMapController.addMapItem(item);
		onFinishAdding(false);
		mDisplay.getSaveButton().setText(DrifMaps.Words.Save());
		
	}
	
	/**
	 * Click handler for map
	 * Works only if current state is @value {@value State#Adding} {@value State#Editing}
	 */
	private MapClickHandler mClickHandler = new MapClickHandler()
	{
		@Override
		public void onClick(MapClickEvent event)
		{
			onMapClick(event);
		}
	};
	
	/**
	 * 
	 * @param event
	 */
	public void onMapClick(MapClickEvent event)
	{
		if(mState == State.Adding || mState==State.Editing)
		{
			LatLng e = event.getLatLng();
			if(e == null)
				e = event.getOverlayLatLng();
			mDataModel.getX().setValue(e.getLongitude());
			mDataModel.getY().setValue(e.getLatitude());
			mDisplay.getStreetView().setLocation(e);
			handleChangePosition(e);
		}
	}
	/**
	 * sets text boxed base on current marker position
	 * @param latlng
	 */
	private void handleChangePosition(LatLng latlng)
	{
		ReverseGeocoder.reverseGeocode(latlng, new ReverseGeocoderCallback()
		{
			@Override
			public void onSuccess(ExtendedPlacemark placemark)
			{
				onSuccessReverseGeocode(placemark);
			}
			
			@Override
			public void onFailure(LatLng point){}
		});
	}
	
	public void onSuccessReverseGeocode(ExtendedPlacemark placemark)
	{
		try
		{
			mDataModel.getCity().setValue(placemark.getCity());
			mDataModel.getStreet().setValue(placemark.getStreet());
			mDataModel.getCountry().setValue(placemark.getCountry());
		}
		catch(Exception e)
		{/*just ignor error and let user write it */}
	}
	
	public void onClearContextItems()
	{
		mDisplay.getContextItemsContainer().clear();
	}
	
}
