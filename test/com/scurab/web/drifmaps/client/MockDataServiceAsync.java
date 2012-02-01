package com.scurab.web.drifmaps.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.scurab.web.drifmaps.shared.datamodel.Detail;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;
import com.scurab.web.drifmaps.shared.datamodel.SearchResult;
import com.scurab.web.drifmaps.shared.datamodel.Star;

public class MockDataServiceAsync implements DataServiceAsync
{

	@Override
	public void get(String className, double x1, double x2, double y1, double y2, boolean deep, AsyncCallback<List<?>> callback)
	{
		if(listener != null)
			listener.get(className, x1, x2, y1, y2, deep, callback);
		else
			callback.onFailure(new Exception("Not implemented and listener is not set!"));
	}

	@Override
	public void get(String className, AsyncCallback<List<?>> callback)
	{
		if(listener != null)
			listener.get(className, callback);
		else
			callback.onFailure(new Exception("Not implemented and listener is not set!"));
	}

	@Override
	public void processDetail(Detail d, int operation, Long parentId, AsyncCallback<Detail> callback)
	{
		callback.onFailure(new Exception("Not implemented!"));
	}

	@Override
	public void processMapItem(MapItem s, int operation, AsyncCallback<MapItem> callback)
	{
		if(operation == DataService.ADD)
			s.setId(System.currentTimeMillis());
		
		callback.onSuccess(s);
	}

	@Override
	public void processStar(Star s, int operation, AsyncCallback<Star> callback)
	{
		if(operation == DataService.ADD)
			s.setId(System.currentTimeMillis());
		
		callback.onSuccess(s);
	}
		
	@Override
	public void processString(String value, int type, int operation, AsyncCallback<Void> callback)
	{
		callback.onSuccess(null);
	}

	@Override
	public void search(String expression, AsyncCallback<List<SearchResult>> callback)
	{
		callback.onFailure(new Exception("Not implemented!"));
	}

	@Override
	public void search(String expression, double x, double y, int range, AsyncCallback<List<SearchResult>> callback)
	{
		callback.onFailure(new Exception("Not implemented!"));
	}

	@Override
	public void getMapItemTypes(AsyncCallback<List<String>> callback)
	{
		callback.onFailure(new Exception("Not implemented!"));
		
	}

	@Override
	public void get(String className, String filters, boolean deep, AsyncCallback<List<?>> callback)
	{
		if(listener != null)
			listener.get(className, filters, deep, callback);
		else
			callback.onFailure(new Exception("Not implemented and listener is not set!"));
	}
	
	public void setOnGetListener(OnGetListener listener)
	{
		this.listener = listener;
	}
	
	private OnGetListener listener;
	public interface OnGetListener
	{
		void get(String className, AsyncCallback<List<?>> callback);
		void get(String className, String filters, boolean deep, AsyncCallback<List<?>> callback);
		void get(String className, double x1, double x2, double y1, double y2, boolean deep, AsyncCallback<List<?>> callback);
	}

}
