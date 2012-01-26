package com.scurab.web.drifmaps.shared.interfaces;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.scurab.web.drifmaps.client.DataService;

public interface MapItemTypeService
{
	/**
	 * @see {@link DataService#processString(String, int, int)}
	 */
	void processString(String value, int type, int operation, AsyncCallback<Void> callback);
	void getMapItemTypes(AsyncCallback<List<String>> callback);
}
