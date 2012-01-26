package com.scurab.web.drifmaps.shared.datamodel;

import java.io.Serializable;
import java.util.List;

import com.scurab.web.drifmaps.client.AppConstants;
import com.scurab.web.drifmaps.shared.interfaces.HasCoordinates;
import com.scurab.web.drifmaps.shared.interfaces.HasIcon;
import com.scurab.web.drifmaps.shared.interfaces.HasId;
import com.scurab.web.drifmaps.shared.interfaces.HasTitle;

public class MapItem implements Serializable, HasCoordinates, HasId, HasIcon, HasTitle
{
	
	private long id;
	private String type;
	
	private String name;
	private String country;
	private String city;
	private String street;
	private String web;
	private String streetViewLink;
	private String author;
	private String contact;
	
	private double x;
	private double y;
	
	private List<String> pros;
	private List<String> cons;
	private List<Detail> details;
	
	@Override
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public String getStreet()
	{
		return street;
	}
	public void setStreet(String street)
	{
		this.street = street;
	}
	public String getWeb()
	{
		return web;
	}
	public void setWeb(String link)
	{
		web = link;
	}
	public String getStreetViewLink()
	{
		return streetViewLink;
	}
	public void setStreetViewLink(String streetViewLink)
	{
		this.streetViewLink = streetViewLink;
	}
	public String getAuthor()
	{
		return author;
	}
	public void setAuthor(String author)
	{
		this.author = author;
	}
	@Override
	public double getX()
	{
		return x;
	}
	public void setX(double x)
	{
		this.x = x;
	}
	@Override
	public double getY()
	{
		return y;
	}
	public void setY(double y)
	{
		this.y = y;
	}
	public List<String> getPros()
	{
		return pros;
	}
	public void setPros(List<String> pros)
	{
		this.pros = pros;
	}
	public List<String> getCons()
	{
		return cons;
	}
	public void setCons(List<String> cons)
	{
		this.cons = cons;
	}
	public List<Detail> getDetails()
	{
		return details;
	}
	public void setDetails(List<Detail> details)
	{
		this.details = details;
	}
	public String getCountry()
	{
		return country;
	}
	public void setCountry(String country)
	{
		this.country = country;
	}
	@Override
	public String getIconUrl()
	{
		return AppConstants.MediumIcons.ICO_BEER;
	}
	@Override
	public String getTitle()
	{
		return name;
	}
	public String getContact()
	{
		return contact;
	}
	public void setContact(String contact)
	{
		this.contact = contact;
	}
}
