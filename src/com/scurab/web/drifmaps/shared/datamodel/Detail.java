package com.scurab.web.drifmaps.shared.datamodel;

import java.io.Serializable;
import java.util.Date;

import com.scurab.web.drifmaps.shared.interfaces.IsDetailItem;

public class Detail implements Serializable, IsDetailItem
{
	private long id;
	private String what;
	private String detail;
	private Date time;
	@Override
	public String getWhat()
	{
		return what;
	}
	@Override
	public String getDetail()
	{
		return detail;
	}
	@Override
	public Date getWhen()
	{
		return time;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public Date getTime()
	{
		return time;
	}
	public void setWhen(Date time)
	{
		this.time = time;
	}
	public void setWhat(String what)
	{
		this.what = what;
	}
	public void setDetail(String detail)
	{
		this.detail = detail;
	}
}
