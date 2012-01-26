package com.scurab.web.drifmaps.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.scurab.web.drifmaps.shared.datamodel.Detail;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;
import com.scurab.web.drifmaps.shared.datamodel.SearchResult;
import com.scurab.web.drifmaps.shared.datamodel.Star;

@RemoteServiceRelativePath("dataService")
public interface DataService extends RemoteService {
	
	public static int  ADD = 1;
	public static int  UPDATE = 2;
	public static int  DELETE = 3;
	public static int TYPE_MAPITEM_TYPE = 1;
	
	/**
	 * Returns data depends on class name
	 * @param className - full class name
	 * @return
	 * @throws Exception
	 */
	public List<?> get(String className) throws Exception;
	
	/**
	 * Same as {@link #get(String)} filtered by coords
	 * @param className
	 * @param x1 min x
	 * @param x2 max x
	 * @param y1 min y 
	 * @param y2 max y
	 * @param deep - if true nested object are included, otherwise will be null
	 * @return
	 * @throws Exception
	 */
	public List<?> get(String className, double x1, double x2, double y1, double y2, boolean deep) throws Exception;
	
	/**
	 * Returns data base on filter
	 * @param className
	 * @param filters -  standar sql part after WHERE
	 * @param deep - include nested objects ?
	 * @return
	 * @throws Exception
	 */
	public List<?> get(String className, String filters, boolean deep) throws Exception;
	
	/**
	 * Returns search result
	 * @param expression - to search
	 * @return
	 * @throws Exception
	 */
	public List<SearchResult> search(String expression) throws Exception;
	
	/**
	 * Returns search result according to position and range
	 * @param expression
	 * @param x - your position x
	 * @param y - your position y
	 * @param range - max distance of search result to you in m
	 * @return
	 * @throws Exception
	 */
	public List<SearchResult> search(String expression, double x, double y, int range) throws Exception;
	
	/**
	 * Do operation with star
	 * @param s
	 * @param operation {@value #ADD} {@value #UPDATE} {@value #DELETE}
	 * @return
	 * @throws Exception
	 */
	public Star processStar(Star s, int operation) throws Exception;
	
	/**
	 * Do operation with MapItem
	 * @param s
	 * @param operation {@value #ADD} {@value #UPDATE} {@value #DELETE}
	 * @return
	 * @throws Exception
	 */
	public MapItem processMapItem(MapItem s, int operation) throws Exception;
	
	/**
	 * Do operation with Detail
	 * @param s
	 * @param operation {@value #ADD} {@value #UPDATE} {@value #DELETE}
	 * @param must be defined only for {@value #ADD} operation, otherwise is meaningless => can be null
	 * @return
	 * @throws Exception
	 */
	public Detail processDetail(Detail d, int operation, Long parentId) throws Exception;
	
	/**
	 * Do operation with Strings
	 * @param s
	 * @param type {@value #TYPE_MAPITEM_TYPE}
	 * @param operation {@value #ADD} {@value #DELETE}
	 * @return
	 * @throws Exception
	 */
	void processString(String value, int type, int operation) throws Exception;
	
	List<String> getMapItemTypes() throws Exception;
	
}
