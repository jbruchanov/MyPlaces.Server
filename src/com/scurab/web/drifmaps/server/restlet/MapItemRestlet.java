package com.scurab.web.drifmaps.server.restlet;

import java.util.List;

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
import com.scurab.web.drifmaps.client.DataService;
import com.scurab.web.drifmaps.server.DataServiceImpl;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;

public class MapItemRestlet extends ServerResource
{
	DataServiceImpl dsi = new DataServiceImpl();

	Gson mGson = WebServiceServer.sGson;

	@SuppressWarnings({ "unchecked" })
	@Get
	public void doGet() throws Exception
	{
		try
		{
			double x1 = 0;
			double y1 = 0;
			double x2 = 0;
			double y2 = 0;
			String itemId = null;
			itemId = (String) getRequest().getAttributes().get("id");
			if (itemId == null)
			{
				try
				{
					x1 = Double.parseDouble(getRequest().getAttributes().get("x1").toString());
					y1 = Double.parseDouble(getRequest().getAttributes().get("y1").toString());
					x2 = Double.parseDouble(getRequest().getAttributes().get("x2").toString());
					y2 = Double.parseDouble(getRequest().getAttributes().get("y2").toString());
				}
				catch (Exception e)
				{
					x1 = 0;
					y1 = 0;
					x2 = 0;
					y2 = 0;
				}
			}

			List<MapItem> data = null;
			if (itemId == null)
				data = (List<MapItem>) dsi.get(MapItem.class, x1, y1, x2, y2, false);
			else
				data = (List<MapItem>) dsi.get(MapItem.class, String.format("id = %s", itemId), true);

			String result = mGson.toJson(data);

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
			MapItem s = mGson.fromJson(value, MapItem.class);
			dsi.processMapItem(s, DataService.UPDATE);
			getResponse().setStatus(Status.SUCCESS_OK);
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
			MapItem s = mGson.fromJson(value, MapItem.class);
			MapItem saved = dsi.processMapItem(s, DataService.ADD);
			getResponse().setEntity(new StringRepresentation(mGson.toJson(saved), MediaType.APPLICATION_JSON));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL, e);
		}

	}

	@Delete()
	public void doDelete() throws Exception
	{
		try
		{
			MapItem s = new MapItem();
			s.setId(Long.parseLong(getRequestAttributes().get("id").toString()));
			dsi.processMapItem(s, DataService.DELETE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			getResponse().setStatus(Status.SERVER_ERROR_INTERNAL, e);
		}
	}
}