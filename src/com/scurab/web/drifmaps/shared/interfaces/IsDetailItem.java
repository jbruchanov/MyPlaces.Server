package com.scurab.web.drifmaps.shared.interfaces;

import java.util.Date;

public interface IsDetailItem
{
	public String getWhat();
	public String getDetail();
	public Date getWhen();
	public void setWhat(String s);
	public void setDetail(String s);
	public void setWhen(Date d);
}
