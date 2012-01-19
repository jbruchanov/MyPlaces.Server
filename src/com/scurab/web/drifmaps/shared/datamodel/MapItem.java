package com.scurab.web.drifmaps.shared.datamodel;

import java.io.Serializable;
import java.util.List;

import com.google.gwt.dev.javac.asm.CollectClassData.ClassType;

public class MapItem implements Serializable
{
	
	public long id;
	public String type;
	
	public String name;
	private String country;
	public String city;
	public String street;
	public String webLink;
	public String streetViewLink;
	public String author;
	
	public double x;
	public double y;
	
	public List<String> pros;
	public List<String> cons;
	public List<Detail> details;
	
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
	public String getWebLink()
	{
		return webLink;
	}
	public void setWebLink(String webLink)
	{
		this.webLink = webLink;
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
	public double getX()
	{
		return x;
	}
	public void setX(double x)
	{
		this.x = x;
	}
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
}
