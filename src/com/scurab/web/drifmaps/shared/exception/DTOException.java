package com.scurab.web.drifmaps.shared.exception;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DTOException extends Exception implements IsSerializable
{

	public DTOException()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public DTOException(String message, Throwable cause)
	{
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DTOException(String message)
	{
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DTOException(Throwable cause)
	{
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
