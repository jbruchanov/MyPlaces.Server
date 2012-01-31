package com.scurab.web.drifmaps.server;

<<<<<<< HEAD
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.Properties;
=======
import java.io.File;
>>>>>>> 5347438827bd7f9051fd4b6e6c320843594edc87

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

<<<<<<< HEAD
import com.scurab.web.drifmaps.client.DrifMaps;
=======
>>>>>>> 5347438827bd7f9051fd4b6e6c320843594edc87
import com.scurab.web.drifmaps.database.Database;



public class MainApplication implements ServletContextListener
{
	private static Properties properties = null;
	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		try
		{
<<<<<<< HEAD
			System.out.print("Starting Drifmaps...\n");
			readPropertiesFile();
			checkForDataStructure();
//			Database d = new Database(DataServiceImpl.FILE);
//			d.createTables();
		}
		catch(Exception e)
		{
			System.err.print(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void checkForDataStructure()
	{
		System.out.print("[Drifmaps DataStructureCheck]\n");
		try
		{
			Database db = new Database();
			ResultSet rs = db.executeQuery(Database.Structure.Queries.SELECT_TABLES_COUNT_FROM_SQLITE_MASTER);
			if(rs.next())
			{
				int tables = rs.getInt("Cnt");
				System.out.print(String.format("[Drifmaps DataStructureCheck] FoundTables:%s\n",tables));
				if(tables == 0)
				{
					System.out.print("[Drifmaps DataStructureCheck] CreatingTables\n");
					db.createTables();
				}
				
			}
			rs.close();
		}
		catch(Exception e)
		{
			System.err.print("[Drifmaps DataStructureCheckError]\n");
			e.printStackTrace();
		}
	}
	
	private static final String PROP_FILE = "app.properties";

	public void readPropertiesFile()
	{
		try
		{
			InputStream is = DrifMaps.class.getResourceAsStream(PROP_FILE);
			properties = new Properties();
			properties.load(is);			
			for(Object okey : properties.keySet())
			{
				String key = okey.toString();
				System.out.print(String.format("[Drifmaps Properties]: %s='%s'\n",key,properties.getProperty(key)));
			}
			
			if(System.getProperty("os.name").toLowerCase().contains("windows"))
				Database.DBFILE = properties.getProperty("dbwin");
			else
				Database.DBFILE = properties.getProperty("dblinux");
			is.close();
		}
		catch (Exception e)
		{
			System.err.println("Failed to read from " + PROP_FILE + " file.");
		}
=======
			File f = new File(DataServiceImpl.FILE);
			if (!f.exists())
			{
				Database d = new Database(DataServiceImpl.FILE);
				d.createTables();
			}
		}
		catch (Exception e)
		{}

>>>>>>> 5347438827bd7f9051fd4b6e6c320843594edc87
	}

}
