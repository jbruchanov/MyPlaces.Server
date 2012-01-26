package com.scurab.web.drifmaps.shared.exception;

public class NotUniqueValueException extends Exception
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
