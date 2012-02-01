package com.scurab.web.drifmaps.server.restlet;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;
import com.scurab.web.drifmaps.client.DataService;
import com.scurab.web.drifmaps.server.DataServiceImpl;
import com.scurab.web.drifmaps.shared.datamodel.Star;

public class StarRestlet extends ServerResource
{

	Gson mGson = new Gson();
	DataServiceImpl dsi = new DataServiceImpl();
	
	@Override
	public void init(Context context, Request request, Response response)
	{
		super.init(context, request, response);
	}

	@Get("json")
	public void getStars() throws Exception 
	{	
		String result = mGson.toJson(dsi.get(Star.class));		
		getResponse().setEntity(new StringRepresentation(result, MediaType.APPLICATION_JSON));
	}	
	
	@Put(value="json")
    public void update(String value) throws Exception 
    {  				
		Gson gs = new Gson();
		Star s = gs.fromJson(value, Star.class);
		dsi.processStar(s, DataService.UPDATE);
	}
		
	@Post(value="json")
    public void add(String value) throws Exception 
    {
		Gson gs = new Gson();
		Star s = gs.fromJson(value, Star.class);
		dsi.processStar(s, DataService.ADD);
    }
	
	@Delete()
	public void delete(String value) throws Exception
	{
		Gson gs = new Gson();
		Star s = gs.fromJson(value, Star.class);
		dsi.processStar(s, DataService.DELETE);
	}
}
