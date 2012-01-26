package com.scurab.web.drifmaps.shared;

import java.util.HashMap;
import junit.framework.Assert;

import com.google.gwt.dev.util.collect.Lists;
import com.google.gwt.junit.client.GWTTestCase;
import com.scurab.web.drifmaps.shared.datamodel.Detail;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;
import com.scurab.web.drifmaps.shared.datamodel.Star;
public final class DataComparator extends GWTTestCase
{	
	public static void assertEquals(Detail o1, Detail o2, boolean assertIds)
	{		
		if(assertIds)
			Assert.assertEquals(o1.getId(),o2.getId());
		Assert.assertEquals(o1.getDetail(),o2.getDetail());
		Assert.assertEquals(o1.getWhat(),o2.getWhat());
		Assert.assertEquals(o1.getTime(),o2.getTime());
	}
	
	public static void assertEquals(Star o1, Star o2,boolean assertIds)
	{
		if(assertIds)
			Assert.assertEquals(o1.getId(),o2.getId());
		Assert.assertEquals(o1.getNote(),o2.getNote());
		Assert.assertEquals(o1.getX(),o2.getX());
		Assert.assertEquals(o1.getY(),o2.getY());
		Assert.assertEquals(o1.getType(),o2.getType());
	}
	
	public static void assertEquals(MapItem o1, MapItem o2, boolean assertIds)
	{
		
	}
	public static void assertEquals(MapItem o1, MapItem o2, boolean assertIds, boolean deep)
	{
		if(assertIds)
			Assert.assertEquals(o1.getId(),o2.getId());
		
		Assert.assertEquals(o1.getAuthor(),o2.getAuthor());
		Assert.assertEquals(o1.getCity(),o2.getCity());
		Assert.assertEquals(o1.getContact(),o2.getContact());
		Assert.assertEquals(o1.getCity(),o2.getCity());
		Assert.assertEquals(o1.getCountry(),o2.getCountry());
		Assert.assertEquals(o1.getName(),o2.getName());
		Assert.assertEquals(o1.getStreet(),o2.getStreet());
		Assert.assertEquals(o1.getStreetViewLink(),o2.getStreetViewLink());
		Assert.assertEquals(o1.getType(),o2.getType());
		Assert.assertEquals(o1.getWeb(),o2.getWeb());
		Assert.assertEquals(o1.getX(),o2.getX());
		Assert.assertEquals(o1.getY(),o2.getY());
		
		if(!deep)
		{
			//should be null, all of them
			Assert.assertEquals(o1.getDetails(),o2.getDetails());
			Assert.assertEquals(o1.getPros(),o2.getPros());
			Assert.assertEquals(o1.getCons(),o2.getCons());
		}
		else
		{
			Assert.assertEquals(o1.getDetails().size(), o2.getDetails().size());
			HashMap<Long,Detail> help1 = new HashMap<Long, Detail>();
			HashMap<Long,Detail> help2 = new HashMap<Long, Detail>();
			for(int i = 0;i<o1.getDetails().size();i++)
			{
				Detail d1 = o1.getDetails().get(i);
				Detail d2 = o2.getDetails().get(i);
				help1.put(d1.getId(),d1);
				help2.put(d2.getId(),d2);
			}
			
			for(long l : help1.keySet())
			{
				Detail d1 = help1.get(l);
				Detail d2 = help2.get(l);
				Assert.assertNotNull(d1);
				Assert.assertNotNull(d2);
				assertEquals(d1,d2,true);
			}
			
			Assert.assertEquals(o1.getPros().size(), o2.getPros().size());
			Lists.sort(o1.getPros());
			Lists.sort(o2.getPros());
			for(int i = 0;i<o1.getPros().size();i++)
				Assert.assertEquals(o1.getPros().get(i), o2.getPros().get(i));
			
			Lists.sort(o1.getCons());
			Lists.sort(o2.getCons());
			Assert.assertEquals(o1.getCons().size(), o2.getCons().size());
			for(int i = 0;i<o1.getCons().size();i++)
				Assert.assertEquals(o1.getCons().get(i), o2.getCons().get(i));
		}
	}

	/**
	 * Never called like a test => not neccessary
	 */
	@Override
	public String getModuleName()
	{
		return null;
	}
}
