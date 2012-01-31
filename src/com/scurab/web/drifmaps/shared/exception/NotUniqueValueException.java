package com.scurab.web.drifmaps.shared.exception;

import com.google.gwt.user.client.rpc.IsSerializable;

public class NotUniqueValueException extends DTOException implements IsSerializable
{
	public NotUniqueValueException(String value)
	{
		super("Not unique value:" + value);
	}
	
	public NotUniqueValueException(String message, String value)
	{
		super(message + " " + value);
	}
}
