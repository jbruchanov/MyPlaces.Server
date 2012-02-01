package com.scurab.web.drifmaps.client.presenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.scurab.web.drifmaps.client.DataServiceAsync;
import com.scurab.web.drifmaps.client.MockDataServiceAsync;
import com.scurab.web.drifmaps.client.component.ContextItem;
import com.scurab.web.drifmaps.client.component.DetailItem;
import com.scurab.web.drifmaps.client.form.MapItemDetailForm;
import com.scurab.web.drifmaps.client.view.MainView;
import com.scurab.web.drifmaps.shared.datamodel.Detail;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;
import com.scurab.web.drifmaps.shared.exception.ValidationException;
import com.scurab.web.drifmaps.shared.utils.RandomGenerator;

public class MainViewPresenterTest extends GWTTestCase
{
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
	
	MockDataServiceAsync mds = new MockDataServiceAsync();
	

	@Test
	public void testAll()
	{
		mds.setOnGetListener(new MockDataServiceAsync.OnGetListener()
		{
			
			@Override
			public void get(String className, double x1, double x2, double y1, double y2, boolean deep, AsyncCallback<List<?>> callback){}
			
			@Override
			public void get(String className, String filters, boolean deep, AsyncCallback<List<?>> callback){}
			
			@Override
			public void get(String className, AsyncCallback<List<?>> callback){}
		});
		
		Maps.loadMapsApi("ABQIAAAAZ3LzS5n2kDiopvOBUxlJwxSBtpmqjM9_4xe7GwB5qdGzFHEeZhQjbZf1kWCfUIvrO9rMtDj_dx87mg", "2", false,
				new Runnable()
				{
					@Override
					public void run()
					{
						try
						{
							tstOnAddingItem();
							tstBinding();
							tstOnEditingItem();
							tstAddingContextIntoPanel();
							tstAddingContextIntoDataModel();
							tstAddItem();
							tstEditItem();
							tstDeletingContextPanelItems();
						}
						catch(Exception e)
						{
							fail(e.getMessage());
						}
						finishTest();
					}
				});
		delayTestFinish(50000);
	}

	/**
	 * Testing internal object states on adding state
	 */
	private void tstOnAddingItem()
	{
		
		MainViewPresenter.Display display = new MainView(mds);
		MainViewPresenter mvp = new MainViewPresenter(display, mds);

		defaultValuesTest(display);
		mvp.onAddingItem();
		editingValuesTest(display);
	}

	private void defaultValuesTest(MainViewPresenter.Display display)
	{
		// init value
		assertTrue(display.getLeftButton().isVisible());
		assertEquals(display.getRightButton().getText(), "Cancel");
		assertFalse(display.getRightButton().isVisible());
		assertNull(display.getDataModel().getValue());
		assertFalse(display.getMenuContent().isOpen());
		assertTrue(display.getLeftButton().isEnabled());
	}

	private void editingValuesTest(MainViewPresenter.Display display)
	{
		assertTrue(display.getLeftButton().isVisible());
		assertEquals(display.getRightButton().getText(), "Cancel");
		assertTrue(display.getRightButton().isVisible());
		assertTrue(display.getMenuContent().isOpen());
		assertTrue(display.getLeftButton().isEnabled());
		assertNotNull(display.getDataModel().getValue());

		MapItem mi = display.getDataModel().getValue();
		assertNull(mi.getAuthor());
		assertNull(mi.getCity());
		assertNull(mi.getContact());
		assertNull(mi.getCountry());
		assertNull(mi.getName());
		assertNull(mi.getStreet());
		assertNull(mi.getStreetViewLink());
		assertNull(mi.getType());
		assertNull(mi.getWeb());
	}

	/**
	 * Test for binding
	 */
	private void tstBinding()	
	{
		MainViewPresenter.Display display = new MainView(mds);
		MainViewPresenter mvp = new MainViewPresenter(display, mds);

		mvp.onAddingItem();
		MapItemDetailForm form = display.getForm();
		MapItem mi = RandomGenerator.genMapItem(false);

		form.getName().setValue(mi.getName(), true);
		form.getStreet().setValue(mi.getStreet(), true);
		form.getCity().setValue(mi.getCity(), true);
		form.getContact().setValue(mi.getContact(), true);
		form.getCountry().setValue(mi.getCountry(), true);
		form.getMapItemType().setValue(mi.getType(), true);
		form.getWeb().setValue(mi.getWeb(), true);
		form.getAuthor().setValue(mi.getAuthor(), true);
		form.getStreetViewWidget().setValue(mi.getStreetViewLink(), true);

		MapItem model = display.getDataModel().getValue();

		assertEquals(mi.getName(), model.getName());
		assertEquals(mi.getAuthor(), model.getAuthor());
		assertEquals(mi.getCity(), model.getCity());
		assertEquals(mi.getContact(), model.getContact());
		assertEquals(mi.getCountry(), model.getCountry());
		assertEquals(mi.getStreet(), model.getStreet());
		assertEquals(mi.getStreetViewLink(), model.getStreetViewLink());
		assertEquals(mi.getType(), model.getType());
		assertEquals(mi.getWeb(), model.getWeb());

		mvp.onFinishAdding(true);
		assertNull(display.getDataModel().getValue());
		finishTest();
	}

	/**
	 * Testing internal object states on editing state
	 */
	private void tstOnEditingItem()
	{
		MainViewPresenter.Display display = new MainView(mds);
		MainViewPresenter mvp = new MainViewPresenter(display, mds);

		mvp.onAddingItem();
		editingValuesTest(display);
		mvp.onFinishAdding(true);
		defaultValuesTest(display);
	}

	/**
	 * Testing adding values into context panel
	 */
	private void tstAddingContextIntoPanel()
	{
		MainViewPresenter.Display display = new MainView(mds);
		MainViewPresenter mvp = new MainViewPresenter(display, mds);

		mvp.onAddingItem();
		MapItemDetailForm form = display.getForm();
		MapItem mi = RandomGenerator.genMapItem(true);

		// pros
		int size = howMany(display.getContextItemsContainer().iterator());
		assertEquals(0, size);
		for (String pro : mi.getPros())
			mvp.onAddCon(pro, false);
		int sizePros = mi.getPros().size();
		size = howMany(display.getContextItemsContainer().iterator());
		assertEquals(sizePros, size);

		// cons
		int sizeCons = mi.getCons().size();
		for (String con : mi.getCons())
			mvp.onAddCon(con, false);
		int size2 = howMany(display.getContextItemsContainer().iterator());
		assertEquals(sizePros + sizeCons, size2);

		// details
		int sizeDetails = mi.getDetails().size();
		for (Detail d : mi.getDetails())
			mvp.onAddDetail(d, false);
		int size3 = howMany(display.getContextItemsContainer().iterator());
		assertEquals(sizePros + sizeCons + sizeDetails, size3);

		mvp.onFinishAdding(true);
		int size4 = howMany(display.getContextItemsContainer().iterator());
		assertEquals(0, size4);

		assertNull(display.getDataModel().getValue());
	}
	
	/**
	 * Testing removing values from context panel
	 */
	private void tstDeletingContextPanelItems()
	{
		MainViewPresenter.Display display = new MainView(mds);
		MainViewPresenter mvp = new MainViewPresenter(display, mds);

		mvp.onAddingItem();
		MapItemDetailForm form = display.getForm();
		MapItem mi = RandomGenerator.genMapItem(true);

		for (String pro : mi.getPros())
			mvp.onAddPro(pro, true);
		for (String con : mi.getCons())
			mvp.onAddCon(con, true);
		for (Detail d : mi.getDetails())
			mvp.onAddDetail(d, true);
		
		int size3 = howMany(display.getContextItemsContainer().iterator());
		assertEquals(mi.getPros().size() + mi.getCons().size() + mi.getDetails().size(), size3);
		
		Iterator<Widget> iterator = display.getContextItemsContainer().iterator();
		List<ContextItem<String>> pros = new ArrayList<ContextItem<String>>();
		List<ContextItem<String>> cons = new ArrayList<ContextItem<String>>();
		List<DetailItem<Detail>> details = new ArrayList<DetailItem<Detail>>();
		while(iterator.hasNext())
		{
			Widget w = iterator.next();
			if(w instanceof ContextItem<?>)
			{
				ContextItem<String> ci = (ContextItem<String>) w; 
				if(mi.getPros().contains(ci.getTextValue()))
					pros.add(ci);
				else if(mi.getCons().contains(ci.getTextValue()))
					cons.add(ci);
				else
					fail();
			}
			else if(w instanceof DetailItem<?>)
			{
				details.add((DetailItem<Detail>) w);
			}
		}
		assertEquals(size3,pros.size() + cons.size()+ details.size());
		
		//remove details
		for(DetailItem<Detail> d : details)
			mvp.onRemoveDetail(d);
		int size4 = howMany(display.getContextItemsContainer().iterator());
		assertEquals(size4, pros.size() + cons.size());
		//check if data was removed from datamodel
		assertEquals(0, display.getDataModel().getValue().getDetails().size());
		
		//remove pros
		for(ContextItem<String> i : pros)
			mvp.onRemovePro(i);
		int size5 = howMany(display.getContextItemsContainer().iterator());
		assertEquals(size5, cons.size());
		assertEquals(0, display.getDataModel().getValue().getPros().size());
		
		//remove cons
		for(ContextItem<String> i : cons)
			mvp.onRemoveCon(i);
		int size6 = howMany(display.getContextItemsContainer().iterator());
		assertEquals(0, size6);
		assertEquals(0, display.getDataModel().getValue().getCons().size());
		
		mvp.onFinishAdding(true);
		assertNull(display.getDataModel().getValue());
	}

	/**
	 * Testing if added data into context panel are insterted into data model
	 */
	private void tstAddingContextIntoDataModel()
	{
		MainViewPresenter.Display display = new MainView(mds);
		MainViewPresenter mvp = new MainViewPresenter(display, mds);

		mvp.onAddingItem();
		MapItemDetailForm form = display.getForm();
		MapItem mi = RandomGenerator.genMapItem(true);

		// pros
		int size = mi.getPros().size();
		for (String pro : mi.getPros())
			mvp.onAddPro(pro, true);
		assertEquals(size, display.getDataModel().getValue().getPros().size());

		// cons
		size = mi.getCons().size();
		for (String con : mi.getCons())
			mvp.onAddCon(con, true);
		assertEquals(size, display.getDataModel().getValue().getCons().size());

		// details
		size = mi.getDetails().size();
		for (Detail d : mi.getDetails())
			mvp.onAddDetail(d, true);
		assertEquals(size, display.getDataModel().getValue().getDetails().size());

		MapItem model = display.getDataModel().getValue();
		assertEquals(mi.getPros().size(), model.getPros().size());
		for (int i = 0; i < mi.getPros().size(); i++)
			assertEquals(mi.getPros().get(i), model.getPros().get(i));
		assertEquals(mi.getCons().size(), model.getCons().size());
		for (int i = 0; i < mi.getCons().size(); i++)
			assertEquals(mi.getCons().get(i), model.getCons().get(i));
		assertEquals(mi.getDetails().size(), model.getDetails().size());
		for (int i = 0; i < mi.getDetails().size(); i++)
		{
			Detail o1 = mi.getDetails().get(i);
			Detail o2 = model.getDetails().get(i);
			assertEquals(o1.getDetail(), o2.getDetail());
			assertEquals(o1.getWhat(), o2.getWhat());
			assertEquals(o1.getTime(), o2.getTime());
		}

		mvp.onFinishAdding(true);
		assertNull(display.getDataModel().getValue());
	}

	private int howMany(Iterator it)
	{
		int i = 0;
		while (it.hasNext())
		{
			i++;
			it.next();
		}
		return i;
	}

	private void tstAddItem() throws ValidationException
	{
		final MainViewPresenter.Display display = new MainView(mds);
		MockMainViewPresenter mvp = new MockMainViewPresenter(display, mds);

		mvp.onAddingItem();
		MapItemDetailForm form = display.getForm();
		final MapItem mi = RandomGenerator.genMapItem(true);

		mvp.onAddingItem();
		display.getDataModel().setValue(mi);
		mvp.setSaveButtonToCheck(display.getLeftButton());
		mvp.setOnSavedItemListener(new OnSavedItemListener()
		{
			@Override
			public void onSavedItem(MapItem saved)
			{
				defaultValuesTest(display); // should be in default state
				assertTrue(saved.getId() > 0);
				assertEquals(mi.getName(), saved.getName());
				assertEquals(mi.getAuthor(), saved.getAuthor());
				assertEquals(mi.getCity(), saved.getCity());
				assertEquals(mi.getContact(), saved.getContact());
				assertEquals(mi.getCountry(), saved.getCountry());
				assertEquals(mi.getStreet(), saved.getStreet());
				assertEquals(mi.getStreetViewLink(), saved.getStreetViewLink());
				assertEquals(mi.getType(), saved.getType());
				assertEquals(mi.getWeb(), saved.getWeb());

				assertEquals(mi.getPros().size(), saved.getPros().size());
				for (int i = 0; i < mi.getPros().size(); i++)
					assertEquals(mi.getPros().get(i), saved.getPros().get(i));
				assertEquals(mi.getCons().size(), saved.getCons().size());
				for (int i = 0; i < mi.getCons().size(); i++)
					assertEquals(mi.getCons().get(i), saved.getCons().get(i));
				assertEquals(mi.getDetails().size(), saved.getDetails().size());
				for (int i = 0; i < mi.getDetails().size(); i++)
				{
					Detail o1 = mi.getDetails().get(i);
					Detail o2 = saved.getDetails().get(i);
					assertEquals(o1.getDetail(), o2.getDetail());
					assertEquals(o1.getWhat(), o2.getWhat());
					assertEquals(o1.getTime(), o2.getTime());
				}
				finishTest();
			}
		});
		
		mvp.onSavingItem(); // continue in MockMainViewPresenter.onSavedItem
	}

	public class MockMainViewPresenter extends MainViewPresenter
	{
		private OnSavedItemListener listener = null;

		public MockMainViewPresenter(Display display, DataServiceAsync ds)
		{
			super(display, ds);
		}

		public void setOnSavedItemListener(OnSavedItemListener listener)
		{
			this.listener = listener;
		}

		Button saveButton;

		public void setSaveButtonToCheck(Button saveButton)
		{
			this.saveButton = saveButton;
		}

		@Override
		public void onSavedItem(MapItem item)
		{
			super.onSavedItem(item);
			if (listener != null)
				listener.onSavedItem(item);
		}
	}

	public interface OnSavedItemListener
	{
		void onSavedItem(MapItem item);
	}

	private void editingValuesTest(MainViewPresenter.Display display, MapItem compareTo)
	{
		assertTrue(display.getLeftButton().isVisible());
		assertEquals(display.getRightButton().getText(), "Cancel");
		assertTrue(display.getRightButton().isVisible());
		assertTrue(display.getMenuContent().isOpen());
		assertTrue(display.getLeftButton().isEnabled());
		assertNotNull(display.getDataModel().getValue());

		MapItem mi = display.getDataModel().getValue();
		assertEquals(mi.getName(), compareTo.getName());
		assertEquals(mi.getAuthor(), compareTo.getAuthor());
		assertEquals(mi.getCity(), compareTo.getCity());
		assertEquals(mi.getContact(), compareTo.getContact());
		assertEquals(mi.getCountry(), compareTo.getCountry());
		assertEquals(mi.getStreet(), compareTo.getStreet());
		assertEquals(mi.getStreetViewLink(), compareTo.getStreetViewLink());
		assertEquals(mi.getType(), compareTo.getType());
		assertEquals(mi.getWeb(), compareTo.getWeb());
		assertEquals(mi.getPros().size(), compareTo.getPros().size());
		for (int i = 0; i < mi.getPros().size(); i++)
			assertEquals(mi.getPros().get(i), compareTo.getPros().get(i));
		assertEquals(mi.getCons().size(), compareTo.getCons().size());
		for (int i = 0; i < mi.getCons().size(); i++)
			assertEquals(mi.getCons().get(i), compareTo.getCons().get(i));
		assertEquals(mi.getDetails().size(), compareTo.getDetails().size());
		for (int i = 0; i < mi.getDetails().size(); i++)
		{
			Detail o1 = mi.getDetails().get(i);
			Detail o2 = compareTo.getDetails().get(i);
			assertEquals(o1.getDetail(), o2.getDetail());
			assertEquals(o1.getWhat(), o2.getWhat());
			assertEquals(o1.getTime(), o2.getTime());
		}

		int size = howMany(display.getContextItemsContainer().iterator());
		int context = mi.getDetails().size() + mi.getPros().size() + mi.getCons().size();
		assertEquals(size, context);
	}

	private void tstEditItem() throws ValidationException
	{
		final MapItem mi = RandomGenerator.genMapItem(true);
		MockDataServiceAsync ds = new MockDataServiceAsync();
		ds.setOnGetListener(new MockDataServiceAsync.OnGetListener()
		{
			@Override
			public void get(String className, String filters, boolean deep, AsyncCallback<List<?>> callback)
			{				
				callback.onSuccess(Arrays.asList(new MapItem[]
				{ mi }));
			}

			@Override
			public void get(String className, double x1, double x2, double y1, double y2, boolean deep, AsyncCallback<List<?>> callback)
			{
				callback.onSuccess(Arrays.asList(new MapItem[]
						{ mi }));
			}

			@Override
			public void get(String className, AsyncCallback<List<?>> callback)
			{
			}
		});
		final MainViewPresenter.Display display = new MainView(ds);
		MockMainViewPresenter mvp = new MockMainViewPresenter(display, ds);

		mvp.onAddingItem();
		MapItemDetailForm form = display.getForm();
		

		mvp.onStartEditing(mi);
		editingValuesTest(display, mi);

		mvp.setOnSavedItemListener(new OnSavedItemListener()
		{
			@Override
			public void onSavedItem(MapItem saved)
			{
				defaultValuesTest(display); // should be in default state
				assertTrue(saved.getId() > 0);
				assertEquals(mi.getName(), saved.getName());
				assertEquals(mi.getAuthor(), saved.getAuthor());
				assertEquals(mi.getCity(), saved.getCity());
				assertEquals(mi.getContact(), saved.getContact());
				assertEquals(mi.getCountry(), saved.getCountry());
				assertEquals(mi.getStreet(), saved.getStreet());
				assertEquals(mi.getStreetViewLink(), saved.getStreetViewLink());
				assertEquals(mi.getType(), saved.getType());
				assertEquals(mi.getWeb(), saved.getWeb());

				assertEquals(mi.getPros().size(), saved.getPros().size());
				for (int i = 0; i < mi.getPros().size(); i++)
					assertEquals(mi.getPros().get(i), saved.getPros().get(i));
				assertEquals(mi.getCons().size(), saved.getCons().size());
				for (int i = 0; i < mi.getCons().size(); i++)
					assertEquals(mi.getCons().get(i), saved.getCons().get(i));
				assertEquals(mi.getDetails().size(), saved.getDetails().size());
				for (int i = 0; i < mi.getDetails().size(); i++)
				{
					Detail o1 = mi.getDetails().get(i);
					Detail o2 = saved.getDetails().get(i);
					assertEquals(o1.getDetail(), o2.getDetail());
					assertEquals(o1.getWhat(), o2.getWhat());
					assertEquals(o1.getTime(), o2.getTime());
				}
				finishTest();
			}
		});

		mvp.onSavingItem(); // continue in MockMainViewPresenter.onSavedItem
	}	
}
