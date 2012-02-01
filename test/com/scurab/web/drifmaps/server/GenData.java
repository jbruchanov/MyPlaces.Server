package com.scurab.web.drifmaps.server;

import org.junit.Test;

import com.scurab.web.drifmaps.client.DataService;
import com.scurab.web.drifmaps.shared.utils.RandomGenerator;

import junit.framework.TestCase;


public class GenData extends TestCase
{
	@Test
	public void testGenerateData() throws Exception
	{
		DataServiceImpl dsi = new DataServiceImpl();
		for(int i = 0;i<250;i++)
		{
			dsi.processMapItem(RandomGenerator.genMapItem(false), DataService.ADD);
		}
	}
}
