package com.scurab.web.drifmaps.server;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.scurab.web.drifmaps.database.Database;



public class MainApplication implements ServletContextListener
{

	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		try
		{
			File f = new File(DataServiceImpl.FILE);
			if (!f.exists())
			{
				Database d = new Database(DataServiceImpl.FILE);
				d.createTables();
			}
		}
		catch (Exception e)
		{}

	}

}
