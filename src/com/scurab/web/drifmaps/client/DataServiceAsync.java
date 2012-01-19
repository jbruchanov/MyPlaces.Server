package com.scurab.web.drifmaps.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;

public interface DataServiceAsync {
	
	<T> void get(String className, AsyncCallback<List<T>> callback);

	/**
	 * Map Item
	 */
	void addUpdate(MapItem item, AsyncCallback<MapItem> callback);
}
