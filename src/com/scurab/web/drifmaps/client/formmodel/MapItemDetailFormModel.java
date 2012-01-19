package com.scurab.web.drifmaps.client.formmodel;

import com.google.gwt.core.client.GWT;
import com.pietschy.gwt.pectin.client.bean.BeanModelProvider;
import com.pietschy.gwt.pectin.client.form.FieldModel;
import com.pietschy.gwt.pectin.client.form.FormModel;
import com.pietschy.gwt.pectin.client.form.ListFieldModel;
import com.scurab.web.drifmaps.shared.datamodel.Detail;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;
import com.scurab.web.drifmaps.shared.datamodel.Type;

public class MapItemDetailFormModel extends FormModel
{
	public static abstract class DataProvider extends BeanModelProvider<MapItem>
	{
	}

	private DataProvider itemProvider = GWT.create(DataProvider.class);

	protected final FieldModel<String> type;

	protected final FieldModel<String> name;
	protected final FieldModel<String> city;
	protected final FieldModel<String> street;
	protected final FieldModel<String> country;
	protected final FieldModel<String> webLink;
	protected final FieldModel<String> author;
	protected final ListFieldModel<Detail> details;
	private final FieldModel<Double> x;
	private final FieldModel<Double> y;

	public MapItemDetailFormModel()
	{
		itemProvider.setAutoCommit(true);
		
		name = fieldOfType(String.class).boundTo(itemProvider, "name");
		city = fieldOfType(String.class).boundTo(itemProvider, "city");
		street = fieldOfType(String.class).boundTo(itemProvider, "street");
		country = fieldOfType(String.class).boundTo(itemProvider, "country");
		webLink = fieldOfType(String.class).boundTo(itemProvider, "webLink");
		author = fieldOfType(String.class).boundTo(itemProvider, "author");
		type = fieldOfType(String.class).boundTo(itemProvider, "type");
		details = listOfType(Detail.class).boundTo(itemProvider, "details");
		x = fieldOfType(Double.class).boundTo(itemProvider, "x");
		y = fieldOfType(Double.class).boundTo(itemProvider, "y");
	}
	
	public void setValue(MapItem item)
	{
		itemProvider.setValue(item);
	}
	
	public MapItem getValue()
	{
		return itemProvider.getValue();
	}

	public FieldModel<String> getType()
	{
		return type;
	}

	public FieldModel<String> getName()
	{
		return name;
	}

	public FieldModel<String> getCity()
	{
		return city;
	}

	public FieldModel<String> getStreet()
	{
		return street;
	}

	public FieldModel<String> getWebLink()
	{
		return webLink;
	}

	public FieldModel<String> getAuthor()
	{
		return author;
	}

	public ListFieldModel<Detail> getDetails()
	{
		return details;
	}

	public FieldModel<Double> getX()
	{
		return x;
	}

	public FieldModel<Double> getY()
	{
		return y;
	}

	public FieldModel<String> getCountry()
	{
		return country;
	}
}
