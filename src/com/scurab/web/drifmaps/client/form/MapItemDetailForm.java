package com.scurab.web.drifmaps.client.form;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.pietschy.gwt.pectin.client.components.AbstractDynamicList;
import com.pietschy.gwt.pectin.client.components.ComboBox;
import com.pietschy.gwt.pectin.client.components.EnhancedTextBox;
import com.pietschy.gwt.pectin.client.form.binding.FormBinder;
import com.scurab.web.drifmaps.client.DrifMaps;
import com.scurab.web.drifmaps.client.formmodel.MapItemDetailFormModel;
import com.scurab.web.drifmaps.shared.datamodel.Type;
public class MapItemDetailForm extends VerySimpleForm
{
	private TextBox name = new TextBox();
	private TextBox street = new TextBox();
	private TextBox city = new TextBox();
	private TextBox country = new TextBox();
	private TextBox author = new TextBox();
	private TextBox weblink = new TextBox();
	private Label x = new Label();
	private Label y = new Label();
	private ComboBox<String> type = new ComboBox<String>(new String[] {"A","B","C"});


	private AbstractDynamicList<String> beers = new AbstractDynamicList<String>(DrifMaps.Words.AddBeer())
	{
		@Override
		protected HasValue<String> createWidget(){return new EnhancedTextBox();}
	};
	
	private AbstractDynamicList<String> pros = new AbstractDynamicList<String>(DrifMaps.Words.AddPro())
	{
		@Override
		protected HasValue<String> createWidget(){return new EnhancedTextBox();}
	};
	
	private AbstractDynamicList<String> cons = new AbstractDynamicList<String>(DrifMaps.Words.AddCon())
	{
		@Override
		protected HasValue<String> createWidget(){return new EnhancedTextBox();}
	};
	
//	private AbstractDynamicList<Detail> details = new AbstractDynamicList<Detail>(DrifMaps.Words.AddDetail())
//	{
//		@Override
//		protected HasValue<Detail> createWidget(){return new EnhancedTextBox();}
//	};
	
	private MapItemDetailFormModel mModel = null;
	private FormBinder binder = new FormBinder();

	
	public MapItemDetailForm(MapItemDetailFormModel model)
	{
		mModel = model;
		
		binder.bind(model.getAuthor()).to(author);
		binder.bind(model.getCity()).to(city);
		binder.bind(model.getCountry()).to(country);
		binder.bind(model.getName()).to(name);
		binder.bind(model.getStreet()).to(street);
		binder.bind(model.getType()).to(type);
		binder.bind(model.getWebLink()).to(weblink);
		binder.bind(model.getX()).toTextOf(x);		
		binder.bind(model.getY()).toTextOf(y);
		
		doLayout();
	}
	
	private void doLayout()
	{
		addRow(DrifMaps.Words.Name(), name);
		addRow(DrifMaps.Words.Street(), street);
		addRow(DrifMaps.Words.City(), city);
		addRow(DrifMaps.Words.Country(), country);
		addRow(DrifMaps.Words.Type(), type);		
		addRow(DrifMaps.Words.WebLink(), weblink);
		addRow(DrifMaps.Words.Author(), author);
		addRow(DrifMaps.Words.LatLngX(), x);
		addRow(DrifMaps.Words.LatLngY(), y);
		
//		addTallRow(DrifMaps.Words.Beers(), beers);
//		addTallRow(DrifMaps.Words.Pros(), pros);
//		addTallRow(DrifMaps.Words.Cons(), cons);
	}
	
}	
