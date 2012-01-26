package com.scurab.web.drifmaps.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.scurab.web.drifmaps.shared.datamodel.Detail;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;
import com.scurab.web.drifmaps.shared.datamodel.SearchResult;
import com.scurab.web.drifmaps.shared.datamodel.Star;
import com.scurab.web.drifmaps.shared.interfaces.MapItemTypeService;

public interface DataServiceAsync extends MapItemTypeService {

	void get(String className, double x1, double x2, double y1, double y2, boolean deep, AsyncCallback<List<?>> callback);

	void get(String className, AsyncCallback<List<?>> callback);

	void processDetail(Detail d, int operation, Long parentId, AsyncCallback<Detail> callback);

	void processMapItem(MapItem s, int operation, AsyncCallback<MapItem> callback);

	void processStar(Star s, int operation, AsyncCallback<Star> callback);

	@Override
	void processString(String value, int type, int operation, AsyncCallback<Void> callback);

	void search(String expression, AsyncCallback<List<SearchResult>> callback);

	void search(String expression, double x, double y, int range, AsyncCallback<List<SearchResult>> callback);

	@Override
	void getMapItemTypes(AsyncCallback<List<String>> callback);

	void get(String className, String filters, boolean deep, AsyncCallback<List<?>> callback);
	
	
}
