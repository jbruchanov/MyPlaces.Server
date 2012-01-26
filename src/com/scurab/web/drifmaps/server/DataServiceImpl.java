package com.scurab.web.drifmaps.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.scurab.web.drifmaps.client.DataService;
import com.scurab.web.drifmaps.database.Database;
import com.scurab.web.drifmaps.shared.datamodel.Detail;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;
import com.scurab.web.drifmaps.shared.datamodel.SearchResult;
import com.scurab.web.drifmaps.shared.datamodel.Star;

/**
 * DataService implementation
 * @author Joe Scurab
 *
 */
public class DataServiceImpl extends RemoteServiceServlet implements DataService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3854519752505687611L;
	public static final String FILE = "C:\\temp\\_DrifMaps\\db.sqlite";
	
	private String getExceptionMessage(Exception e)
	{
		String detail = "";
		if(e.getCause() != null) detail = e.getCause().getMessage();
		return String.format("<b>%s</b><br/>%s",e.getMessage(), detail);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<?> get(String className) throws Exception
	{
		try
		{
			Class<?> clazz = Class.forName(className);
			return get(clazz);
		}
		catch(ClassNotFoundException cne)
		{
			throw new Exception(getExceptionMessage(cne));
		}
	}
	
	/**
	 * Implemenation of @see {@link DataService#get(String)}
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public  List<?> get(Class<?> clazz) throws Exception
	{
		Database db = null;
		List<?> result = null;
		try
		{
			db = new Database(FILE);
			
			if(MapItem.class.equals(clazz))
				result = db.get(true);
			else if(Star.class.equals(clazz))
				result = db.getStars();
			else
				throw new Exception("Not implemented!");	
		}
		catch(Exception e)
		{
			throw new Exception(getExceptionMessage(e));
		}
		finally
		{
			try
			{
				if(db != null)
					db.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<?> get(String className, double x1, double y1, double x2, double y2, boolean deep) throws Exception
	{
		try
		{
			Class<?> clazz = Class.forName(className);
			return get(clazz, x1, y1, x2, y2, deep);
		}
		catch(ClassNotFoundException cne)
		{
			throw new Exception(getExceptionMessage(cne));
		}
	}
	
	/**
	 * Implementation of {@see DataService#get(String, float, float, float, float, boolean)
	 * @param clazz to load
	 * @param x1 min x
	 * @param x2 max y
	 * @param y1 min y
	 * @param y2 max y
	 * @param deep - load nested objects, otherwise will be null
	 * @return
	 * @throws Exception
	 */
	public  List<?> get(Class<?> clazz, double x1, double y1, double x2, double y2, boolean deep) throws Exception
	{
		Database db = null;
		List<?> result = null;
		try
		{
			db = new Database(FILE);
			
			if(MapItem.class.equals(clazz))
				result = db.get(x1, y1, x2, y2, deep);
			else if(Star.class.equals(clazz))
				result = db.getStars(x1, y1, x2, y2);
			else
				throw new Exception("Not implemented!");	
		}
		catch(Exception e)
		{
			throw new Exception(getExceptionMessage(e));
		}
		finally
		{
			try
			{
				if(db != null)
					db.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SearchResult> search(String expression) throws Exception
	{
		///TODO
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SearchResult> search(String expression, double x, double y, int range) throws Exception
	{
		///TODO
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Star processStar(Star item, int operation) throws Exception
	{
		Database db = null;
		try
		{
			db = new Database(FILE);
			switch(operation)
			{
				case ADD:
					db.addNewStar(item);
					break;
				case UPDATE:
					db.updateStar(item);
					break;
				case DELETE:
					db.deleteStar(item);
					break;
				default:
					throw new Exception("Not implemented!");	
			}
			return item;
		}
		catch(Exception e)
		{
			throw new Exception(getExceptionMessage(e));
		}
		finally
		{
			try
			{
				if(db != null)
					db.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MapItem processMapItem(MapItem item, int operation) throws Exception
	{
		Database db = null;
		try
		{
			db = new Database(FILE);
			switch(operation)
			{
				case ADD:
					db.addNewMapItem(item);
					break;
				case UPDATE:
					db.updateMapItem(item);
					break;
				case DELETE:
					db.deleteMapItem(item);
					break;
				default:
					throw new Exception("Not implemented!");	
			}
			return item;
		}
		catch(Exception e)
		{
			throw new Exception(getExceptionMessage(e));
		}
		finally
		{
			try
			{
				if(db != null)
					db.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Detail processDetail(Detail item, int operation, Long parentId) throws Exception
	{
		Database db = null;
		try
		{
			db = new Database(FILE);
			switch(operation)
			{
				case ADD:		
					if(parentId == null || parentId == 0)
						throw new IllegalArgumentException("parentId must be defined and >0 !");
					db.addNewDetail(parentId, item);
					break;
				case UPDATE:					
					db.updateDetail(item);
					break;
				case DELETE:
					db.deleteDetail(item);
					break;
				default:
					throw new Exception("Not implemented!");	
			}
			return item;
		}
		catch(Exception e)
		{
			throw new Exception(getExceptionMessage(e));
		}
		finally
		{
			try
			{
				if(db != null)
					db.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void processString(String item, int type, int operation) throws Exception
	{
		Database db = null;
		try
		{
			db = new Database(FILE);
			switch(operation)
			{
				case ADD:					
					if(type == DataService.TYPE_MAPITEM_TYPE)
						db.addNewMapItemItem(item);
					else
						throw new Exception("Not implemented!");
					break;
				case DELETE:
					if(type == DataService.TYPE_MAPITEM_TYPE)
						db.deleteMapItemType(item);
					else
						throw new Exception("Not implemented!");
					break;
				default:
					throw new Exception("Not implemented!");	
			}
		}
		catch(Exception e)
		{
			throw new Exception(getExceptionMessage(e));
		}
		finally
		{
			try
			{
				if(db != null)
					db.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	@Override
	public List<String> getMapItemTypes() throws Exception
	{
		Database db = null;
		List<String> result = null;
		try
		{
			db = new Database(FILE);
			result = db.getMapItemTypes();
		}
		catch(Exception e)
		{
			throw new Exception(getExceptionMessage(e));
		}
		finally
		{
			try
			{
				if(db != null)
					db.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public List<?> get(String className, String filters, boolean deep) throws Exception
	{
		try
		{
			Class<?> clazz = Class.forName(className);
			return get(clazz, filters, deep);
		}
		catch(ClassNotFoundException cne)
		{
			throw new Exception(getExceptionMessage(cne));
		}
	}
	
	public List<?> get(Class<?> clazz, String filters, boolean deep) throws Exception
	{
		Database db = null;
		List<?> result = null;
		try
		{
			db = new Database(FILE);
			
			if(MapItem.class.equals(clazz))
				result = db.getMapItems(filters, true);			
			else
				throw new Exception("Not implemented!");	
		}
		catch(Exception e)
		{
			throw new Exception(getExceptionMessage(e));
		}
		finally
		{
			try
			{
				if(db != null)
					db.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return result;
	}
}
