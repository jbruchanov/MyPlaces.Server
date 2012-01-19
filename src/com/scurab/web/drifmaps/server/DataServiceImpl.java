package com.scurab.web.drifmaps.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.scurab.web.drifmaps.client.DataService;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;

public class DataServiceImpl extends RemoteServiceServlet implements DataService{

	@Override
	public <T> List<T> get(String className) {
		return null;
	}

	@Override
	public MapItem addUpdate(MapItem item) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
