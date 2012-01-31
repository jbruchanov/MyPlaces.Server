package com.scurab.web.drifmaps.shared.exception;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ValidationException extends DTOException implements IsSerializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8544781081294358990L;

	public ValidationException()
	{
		
	}
	
	public ValidationException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

	public ValidationException(String arg0)
	{
		super(arg0);
	}

	public ValidationException(Throwable arg0)
	{
		super(arg0);
	}

}
