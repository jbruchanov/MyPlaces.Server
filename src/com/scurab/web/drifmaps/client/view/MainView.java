package com.scurab.web.drifmaps.client.view;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.scurab.web.drifmaps.client.DataServiceAsync;
import com.scurab.web.drifmaps.client.DrifMaps;
import com.scurab.web.drifmaps.client.component.ContextButtonPanel;
import com.scurab.web.drifmaps.client.form.MapItemDetailForm;
import com.scurab.web.drifmaps.client.formmodel.MapItemDetailFormModel;
import com.scurab.web.drifmaps.client.presenter.MainViewPresenter;
import com.scurab.web.drifmaps.client.widget.StreetViewWidget;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;
public class MainView extends Composite implements MainViewPresenter.Display
{
	private RootPanel top = null;
	private RootPanel menu = null;
	private RootPanel bottom = null;
	private RootPanel workSpace = null;	
	private Menu mMenu = null;
	private StreetViewWidget mStreetView = null;
	private MapWidget mMapWidget = null;
	private MapItemDetailForm mForm = null;
	private ContextButtonPanel mContextButtonPanel = null;
	private FlowPanel mContextContainer = null;
	private FlowPanel mContextItemsContainer = null;
	private MapItemDetailFormModel mFormModel = null;
	private TabPanel mTabPanel = null;
	private Button mSearchButton = null;
	private TextBox mSearchBox = null;
	
	@SuppressWarnings("unused")
	public static void initForTest()
	{
		String s =RootPanel.getBodyElement().getInnerHTML();
		RootPanel.getBodyElement().setInnerHTML(s + "<div class=\"top\" id=\"top\"></div><div class=\"menu\" id=\"menu\"></div><div class=\"center\" id=\"center\"><div class=\"topLeftCenterCorner\"></div><div class=\"bottomLeftCenterCorner\"></div><div class=\"workspace\" id=\"workspace\"></div></div><div class=\"bottom\" id=\"bottom\"></div>");
	}
	public MainView(DataServiceAsync ds)
	{		
		top = RootPanel.get("top");		
		menu = RootPanel.get("menu");
		workSpace = RootPanel.get("workspace");
		bottom = RootPanel.get("bottom");				
		initSearchPanel();
		//add menu to menu container
		mMenu = new Menu();
		getMenuContainer().add(mMenu);
		createMenuContent(ds);
		mMapWidget = new MapWidget();
		workSpace.add(mMapWidget);
		Window.addResizeHandler(new ResizeHandler()
		{
			@Override
			public void onResize(ResizeEvent event)
			{				
				handleResizeContextContainer();
			}
		});
		handleResizeContextContainer();
	}
	
	private void initSearchPanel()
	{
		SimplePanel p = new SimplePanel();
		p.setStyleName("searchBox");
		mSearchBox = new TextBox();
		mSearchBox.setStyleName("searchTextBox");
		mSearchButton = new Button();
		mSearchButton.setStyleName("searchButton");
		p.add(mSearchBox);
		top.add(mSearchButton);
		mSearchButton.setText(DrifMaps.Words.Search());
		top.add(p);
	}
	
	@Override
	public RootPanel getWorkSpace()
	{
		return workSpace;
	}

	@Override
	public RootPanel getMenuContainer()
	{
		return menu;
	}
	
	@Override
	public RootPanel getTopContainer()
	{
		return top;
	}

//	@Override
//	public Menu getMenu()
//	{
//		return mMenu;
//	}
	
	private void createMenuContent(DataServiceAsync ds)
	{
		VerticalPanel panel = new VerticalPanel();		
		mFormModel = new MapItemDetailFormModel();
		mForm = new MapItemDetailForm(mFormModel, ds);
	    panel.add(mForm);
	    mStreetView = mForm.getStreetViewWidget();
	    panel.add(mStreetView);
		
	    mTabPanel = new TabPanel();
		mTabPanel.setWidth("350px");
		mTabPanel.add(panel,"Main");		
		mTabPanel.selectTab(0);		
		mTabPanel.add(createContextTabContainer(),"Context");
		mMenu.getMenuContent().add(mTabPanel);
	}
	
	private VerticalPanel createContextTabContainer()
	{
		VerticalPanel vp = new VerticalPanel();
		mContextButtonPanel = new ContextButtonPanel();
		vp.add(mContextButtonPanel);
		mContextContainer = new FlowPanel();
		vp.add(mContextContainer);
		mContextItemsContainer = new FlowPanel();
		mContextItemsContainer.setStyleName("item-container");
		vp.add(mContextItemsContainer);
		return vp;
	}
	
	/**
	 * Resizing of context container to ensure good height
	 */
	private void handleResizeContextContainer()
	{
		int limit = 550; //depends on style of center minheight
		int height = (Window.getClientHeight() - 250);
		if(height < limit)
			height = limit;
		mContextItemsContainer.setHeight(height + "px");
	}
	
	@Override
	public MapItemDetailForm getForm()
	{
		return mForm;
	}
	
	@Override
	public StreetViewWidget getStreetView()
	{
		return mStreetView;
	}

	@Override
	public HasClickHandlers getPlusButton()
	{
		return mContextButtonPanel.getPlusButton();
	}

	@Override
	public HasClickHandlers getMinusButton()
	{
		return mContextButtonPanel.getMinusButton();
	}

	@Override
	public HasClickHandlers getCustomButton()
	{
		return mContextButtonPanel.getCustomButton();
	}

	@Override
	public HasWidgets getContextItemsContainer()
	{
		return mContextItemsContainer;
	}

	@Override
	public MapWidget getMapWidget()
	{	
		return mMapWidget;
	}

	@Override
	public void setMapItem(MapItem mi)
	{
		mFormModel.setValue(mi);		
	}

	@Override
	public boolean validate()
	{
		return mFormModel.validate();
	}

	@Override
	public MapItemDetailFormModel getDataModel()
	{
		return mFormModel;
	}
	
	@Override
	public Button getAddButton()
	{
		return mMenu.getAddButton();
	}
	
	@Override
	public Button getSaveButton()
	{
		return  mMenu.getSaveButton();
	}
	
	@Override
	public DisclosurePanel getMenuContent()
	{
		return mMenu.getMenuContent();
	}
	@Override
	public void setCurrentMenuTab(int i)
	{
		mTabPanel.selectTab(i);		
	}
	
	public Button getSearchButton()
	{
		return mSearchButton;
	}
	@Override
	public HasText getSearchBox()
	{
		return mSearchBox;
	}
}
