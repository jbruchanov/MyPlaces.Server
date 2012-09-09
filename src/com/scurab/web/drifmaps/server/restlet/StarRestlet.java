package com.scurab.web.drifmaps.server.restlet;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scurab.web.drifmaps.client.DataService;
import com.scurab.web.drifmaps.server.DataServiceImpl;
import com.scurab.web.drifmaps.shared.datamodel.Star;

public class StarRestlet extends ServerResource
{

	Gson mGson = WebServiceServer.sGson;
	DataServiceImpl dsi = new DataServiceImpl();

	@Get
	public void doGet() throws Exception
	{
		try
		{
			String result = mGson.toJson(dsi.get(Star.class));
			getResponse().setEntity(new StringRepresentation(result, MediaType.APPLICATION_JSON));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL, e);
		}
	}

	@Put
	public void doUpdate(String value) throws Exception
	{
		try
		{
			Star s = mGson.fromJson(value, Star.class);
			dsi.processStar(s, DataService.UPDATE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL, e);
		}
	}

	@Post
	public void doPost(String value) throws Exception
	{
		try
		{
			Star s = mGson.fromJson(value, Star.class);
			Star saved = dsi.processStar(s, DataService.ADD);
			getResponse().setEntity(new StringRepresentation(mGson.toJson(saved), MediaType.APPLICATION_JSON));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL, e);
		}
	}

	@Delete
	public void doDelete() throws Exception
	{
		try
		{
			Star s = new Star();
			s.setId(Long.parseLong(getRequestAttributes().get("id").toString()));
			dsi.processStar(s, DataService.DELETE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL, e);
		}
	}
}
