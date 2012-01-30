package com.scurab.web.drifmaps.client.form;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.pietschy.gwt.pectin.client.form.binding.FormBinder;
import com.pietschy.gwt.pectin.client.form.validation.binding.ValidationBinder;
import com.scurab.web.drifmaps.client.DrifMaps;
import com.scurab.web.drifmaps.client.controls.NewItemComboBox;
import com.scurab.web.drifmaps.client.formmodel.MapItemDetailFormModel;
import com.scurab.web.drifmaps.client.widget.StreetViewWidget;
import com.scurab.web.drifmaps.shared.interfaces.MapItemTypeService;

public class MapItemDetailForm extends VerySimpleForm
{
	private ValidationBinder validation = new ValidationBinder();
	private TextBox name = new TextBox();
	private TextBox street = new TextBox();
	private TextBox city = new TextBox();
	private TextBox country = new TextBox();
	private TextBox author = new TextBox();
	private TextBox web = new TextBox();
	private TextBox contact = new TextBox();
	private StreetViewWidget streetView = new StreetViewWidget(false);
	private Label x = new Label();
	private Label y = new Label();
	private NewItemComboBox type = new NewItemComboBox();

	private MapItemDetailFormModel mModel = null;
	private FormBinder binder = new FormBinder();

	
	public MapItemDetailForm(MapItemDetailFormModel model, MapItemTypeService dataService)
	{
		setWidth("100%");
		mModel = model;
		type.setDataService(dataService);
		try
		{
			type.loadItemsFromServer();
		}
		catch(Exception e){/* nevermind */}
		
		binder.bind(model.getAuthor()).to(author);
		binder.bind(model.getCity()).to(city);
		binder.bind(model.getCountry()).to(country);
		binder.bind(model.getName()).to(name);
		binder.bind(model.getStreet()).to(street);
		binder.bind(model.getType()).to(type);
		binder.bind(model.getWeb()).to(web);
		binder.bind(model.getX()).toTextOf(x);		
		binder.bind(model.getY()).toTextOf(y);
		binder.bind(model.getStreetViewLink()).to(streetView);
		binder.bind(model.getContact()).to(contact);
		doLayout();
		
	}
	
	private void doLayout()
	{
		addRow(DrifMaps.Words.Name(), name,createValidationLabel(validation,mModel.getName()));
		addRow(DrifMaps.Words.Street(), street,createValidationLabel(validation,mModel.getStreet()));
		addRow(DrifMaps.Words.City(), city);
		addRow(DrifMaps.Words.Country(), country);
		addRow(DrifMaps.Words.Type(), type, createValidationLabel(validation,mModel.getType()));		
		addRow(DrifMaps.Words.Contact(), contact);
		addRow(DrifMaps.Words.WebLink(), web);
		addRow(DrifMaps.Words.Author(), author);
		addRow(DrifMaps.Words.LatLngX(), x, createValidationLabel(validation,mModel.getX()));
		addRow(DrifMaps.Words.LatLngY(), y, createValidationLabel(validation,mModel.getY()));
		addRow(streetView);
		
	}

	public StreetViewWidget getStreetViewWidget()
	{
		return streetView;
	}

	public TextBox getName()
	{
		return name;
	}

	public TextBox getStreet()
	{
		return street;
	}

	public TextBox getCity()
	{
		return city;
	}

	public TextBox getCountry()
	{
		return country;
	}

	public TextBox getAuthor()
	{
		return author;
	}

	public TextBox getWeb()
	{
		return web;
	}

	public Label getX()
	{
		return x;
	}

	public Label getY()
	{
		return y;
	}

	public NewItemComboBox getMapItemType()
	{
		return type;
	}

	public TextBox getContact()
	{
		return contact;
	}
}	
