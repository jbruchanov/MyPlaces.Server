package com.scurab.web.drifmaps.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.scurab.web.drifmaps.shared.datamodel.MapItem;
import com.scurab.web.drifmaps.shared.datamodel.Type;

public class Database
{	
	private static Connection mConnection = null;
	private static Statement mStatement = null;

	static
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public Database(String fileName) throws SQLException
	{
		mConnection = DriverManager.getConnection(String.format("jdbc:sqlite:%s",fileName));		
	}
	
	public void deleteAll() throws SQLException
	{
		mStatement = mConnection.createStatement();
		for(String table : Structure.Tables.ALL_TABLES)
		{
			String qry = String.format(Structure.Queries.DROP_TABLE_TEMPLATE, table);
			mStatement.executeUpdate(qry);
		}
	}
	
	public List<MapItem> get(float x1, float y1, float x2,float y2)
	{
		return null;
	}
	
	public List<MapItem> get()
	{
		return null;
	}
	
	private List<MapItem> getMapItems() throws SQLException
	{
		List<MapItem> result = new ArrayList<MapItem>();
		HashMap<Long,MapItem> help = new HashMap<Long,MapItem>();
		List<Long> ids = new ArrayList<Long>();
		ResultSet rs = mStatement.executeQuery(Structure.Queries.SELECT_ALL_FROM_MAP_ITEMS);
		while(rs.next())
		{
			MapItem mi = new MapItem();
			mi.id = rs.getLong(Structure.MapItems.ID);
			mi.author = rs.getString(Structure.MapItems.AUTHOR);
			mi.city = rs.getString(Structure.MapItems.CITY);
			mi.name = rs.getString(Structure.MapItems.NAME);
			mi.street = rs.getString(Structure.MapItems.STREET);
			mi.streetViewLink = rs.getString(Structure.MapItems.STREETVIEWLINK);
			mi.webLink = rs.getString(Structure.MapItems.WEBLINK);
			mi.x = rs.getFloat(Structure.MapItems.X);
			mi.y = rs.getFloat(Structure.MapItems.Y);
			mi.type = rs.getString(Structure.MapItems.TYPE);
			ids.add(mi.id);
			help.put(mi.id, mi);
		}
		
		return result;
	}
	
	private static final class Structure
	{
		public static class Tables 
		{
			public static final String MAP_ITEMS = "MapItems";
			public static final String PROS = "Pros";
			public static final String CONS = "Cons";
			public static final String DETAILS = "Details";
			public static final String[] ALL_TABLES = new String[] {DETAILS, CONS,PROS,MAP_ITEMS};
		}
		
		public static class Queries
		{
			public static final String CREATE_TABLE_MAP_ITEM = "CREATE VIRTUAL TABLE MapItems USING fts3( ID NUMERIC PRIMARY KEY NOT NULL, Author TEXT NOT NULL, City TEXT NOT NULL, Name TEXT NOT NULL, Street TEXT NOT NULL, Patio SHORT, WebLink TEXT, StreetViewLink TEXT, X FLOAT NOT NULL, Y FLOAT NOT NULL, Type TEXT)";
			public static final String CREATE_TABLE_PROS = "CREATE VIRTUAL TABLE Pros USING fts3(MapItem_FK NUMERIC NOT NULL, Value TEXT NOT NULL)";
			public static final String CREATE_TABLE_CONS = "CREATE VIRTUAL TABLE Cons USING fts3(MapItem_FK NUMERIC NOT NULL, Value TEXT NOT NULL)";			
			public static final String CREATE_TABLE_DETAILS = "CREATE VIRTUAL TABLE Details USING fts3(MapItem_FK NUMERIC NOT NULL, Value TEXT NOT NULL, When NUMERIC NOT NULL)";
			public static final String DROP_TABLE_TEMPLATE = "DELETE FROM %s";
			public static final String SELECT_ALL_FROM_MAP_ITEMS = String.format("SELECT * FROM %s",Tables.MAP_ITEMS);
		}
		
		public static class MapItems
		{
			public static final String ID = "ID";
			public static final String AUTHOR = "Author";
			public static final String CITY = "City";
			public static final String NAME = "Name";
			public static final String STREET = "Street";
			public static final String WEBLINK = "WebLink";
			public static final String PATIO = "Patio";
			public static final String STREETVIEWLINK = "StreetViewLink";
			public static final String X = "X";
			public static final String Y = "Y";
			public static final String TYPE = "TYPE";
		}
		
		public static class Pros
		{
			public static final String MAPITEM_ID = "MapItem_FK";
			public static final String VALUE = "Value";
		}
		
		public static class Cons
		{
			public static final String MAPITEM_ID = "MapItem_FK";
			public static final String VALUE = "Value";
		}
		
		public static class Details
		{
			public static final String MAPITEM_ID = "MapItem_FK";
			public static final String VALUE = "Value";
			public static final String WHEN = "When";
		}
	}
}