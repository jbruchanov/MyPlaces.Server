package com.scurab.web.drifmaps.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;

@RemoteServiceRelativePath("dataService")
public interface DataService extends RemoteService {
	
	public <T> List<T> get(String className);
	
	/*
	 * Map items
	 */
	public MapItem addUpdate(MapItem item);
}
