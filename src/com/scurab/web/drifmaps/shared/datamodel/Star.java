package com.scurab.web.drifmaps.shared.datamodel;

import java.io.Serializable;

import com.scurab.web.drifmaps.client.AppConstants;
import com.scurab.web.drifmaps.shared.interfaces.HasCoordinates;
import com.scurab.web.drifmaps.shared.interfaces.HasIcon;
import com.scurab.web.drifmaps.shared.interfaces.HasId;
import com.scurab.web.drifmaps.shared.interfaces.HasTitle;

public class Star implements HasCoordinates, HasId, Serializable, HasIcon, HasTitle
{
	private long id;
	private String note;
	private int type;
	private double x;
	private double y;
	
	@Override
	public double getX()
	{
		return x;
	}
	@Override
	public double getY()
	{
		return y;
	}
	@Override
	public long getId()
	{
		return id;
	}
	@Override
	public String getIconUrl()
	{
		return AppConstants.MediumIcons.ICO_STAR;
	}
	@Override
	public String getTitle()
	{
		return note;
	}
	public String getNote()
	{
		return note;
	}
	public void setNote(String note)
	{
		this.note = note;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public void setX(double x)
	{
		this.x = x;
	}
	public void setY(double y)
	{
		this.y = y;
	}
}
