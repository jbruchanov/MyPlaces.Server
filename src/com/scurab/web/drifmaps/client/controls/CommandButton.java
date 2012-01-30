package com.scurab.web.drifmaps.client.controls;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Button;

public class CommandButton extends Button
{
	public CommandButton()
	{
		super();
	}

	public CommandButton(Element element)
	{
		super(element);
	}

	public CommandButton(SafeHtml html, ClickHandler handler)
	{
		super(html, handler);
	}

	public CommandButton(SafeHtml html)
	{
		super(html);
	}

	public CommandButton(String html, ClickHandler handler)
	{
		super(html, handler);
	}

	public CommandButton(String html)
	{
		super(html);
	}

	private String mCommand = null;

	public void setCommand(String cmd)
	{
		mCommand = cmd;
	}
	
	public String getCommand()
	{
		return mCommand;
	}
}
