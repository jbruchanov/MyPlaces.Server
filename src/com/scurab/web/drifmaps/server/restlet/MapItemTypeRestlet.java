package com.scurab.web.drifmaps.server.restlet;

import java.util.List;

import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;
import com.scurab.web.drifmaps.server.DataServiceImpl;

public class MapItemTypeRestlet  extends ServerResource
{
	DataServiceImpl mDataService = new DataServiceImpl();
	Gson mGson = WebServiceServer.sGson;
	
	
	@Get("json")
	public void doGet() throws Exception 
	{	
		Response r = getResponse();
		try
		{
			List<String> data = mDataService.getMapItemTypes();
			String result = mGson.toJson(data);
			r.setEntity(result, MediaType.APPLICATION_JSON);
			r.setStatus(Status.SUCCESS_OK);
		}
		catch(Exception e)
		{
			r.setStatus(Status.CLIENT_ERROR_BAD_REQUEST,e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Post
	public void doPost(String value) throws Exception 
	{	
		Response r = getResponse();
		try
		{
			mDataService.processString(value, DataServiceImpl.TYPE_MAPITEM_TYPE, DataServiceImpl.ADD);
			r.setStatus(Status.SUCCESS_OK);
		}
		catch(Exception e)
		{
			r.setStatus(Status.CLIENT_ERROR_BAD_REQUEST,e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Delete
	public void doDelete() throws Exception 
	{	
		Response r = getResponse();
		try
		{
			String value = String.valueOf(getRequestAttributes().get("name"));
			mDataService.processString(value, DataServiceImpl.TYPE_MAPITEM_TYPE, DataServiceImpl.DELETE);
			r.setStatus(Status.SUCCESS_OK);
		}
		catch(Exception e)
		{
			r.setStatus(Status.CLIENT_ERROR_BAD_REQUEST,e.getMessage());
			e.printStackTrace();
		}
	}
}
