package com.scurab.web.drifmaps.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



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
//			Database d = new Database(DataServiceImpl.FILE);
//			d.createTables();
		}
		catch(Exception e){}
		
	}

}
