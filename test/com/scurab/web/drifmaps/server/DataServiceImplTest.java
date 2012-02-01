package com.scurab.web.drifmaps.server;

import static org.junit.Assert.*;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.scurab.web.drifmaps.client.DataService;
import com.scurab.web.drifmaps.database.Database;
import com.scurab.web.drifmaps.shared.DataComparator;
import com.scurab.web.drifmaps.shared.datamodel.Detail;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;
import com.scurab.web.drifmaps.shared.datamodel.Star;
import com.scurab.web.drifmaps.shared.utils.AppUtils;
import com.scurab.web.drifmaps.shared.utils.RandomGenerator;

public class DataServiceImplTest extends TestCase
{
	DataServiceImpl dsi = new DataServiceImpl();
	Database d = null;
	@Before
	public void setUp() throws Exception
	{
		d = new Database();
		d.dropAll();
		d.createTables();
		d.close();
	}

	@Test
	public void testReCreateStructure()
	{
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testProcessMapItem() throws Exception
	{
		//test add
		MapItem mi = RandomGenerator.genMapItem(true);
		assertEquals(0,dsi.get(MapItem.class.getName()).size());
		dsi.processMapItem(mi, DataService.ADD);
		assertTrue(mi.getId() > 0);
		List<MapItem> mis = (List<MapItem>) dsi.get(MapItem.class.getName()); 
		assertEquals(1,mis.size());
		DataComparator.assertEquals(mi, mis.get(0), true, true);
		
		//test update
		MapItem update = RandomGenerator.genMapItem(true);
		update.setId(mi.getId());
		dsi.processMapItem(update, DataService.UPDATE);
		mis = (List<MapItem>) dsi.get(MapItem.class.getName()); 
		assertEquals(1,mis.size());
		DataComparator.assertEquals(update, mis.get(0), true, true);
		
		//test delete
		dsi.processMapItem(update, DataService.DELETE);
		mis = (List<MapItem>) dsi.get(MapItem.class.getName()); 
		assertEquals(0,mis.size());
		
	}

	@Test
	public void testGetFiltered() throws Exception
	{
		//test add
		MapItem mi = RandomGenerator.genMapItem(true);
		mi.setX(10);
		mi.setY(10);
		assertEquals(0,dsi.get(MapItem.class.getName()).size());
		dsi.processMapItem(mi, DataService.ADD);
		assertTrue(mi.getId() > 0);
		
		List<MapItem> mis = (List<MapItem>) dsi.get(MapItem.class.getName(),0,0,15,15,true); 
		assertEquals(1,mis.size());
		DataComparator.assertEquals(mi, mis.get(0), true, true);
		
		mis = (List<MapItem>) dsi.get(MapItem.class.getName(),0,0,5,5,true); 
		assertEquals(0,mis.size());
		
		//delete
		dsi.processMapItem(mi, DataService.DELETE);
		mis = (List<MapItem>) dsi.get(MapItem.class.getName()); 
		assertEquals(0,mis.size());
	}
	
	@Test
	public void testGetByStringFilter() throws Exception
	{
		//test add
		MapItem mi = RandomGenerator.genMapItem(true);
		mi.setX(10);
		mi.setY(10);
		assertEquals(0,dsi.get(MapItem.class.getName()).size());
		dsi.processMapItem(mi, DataService.ADD);
		assertTrue(mi.getId() > 0);
		
		String filter = "id = " + mi.getId();
		List<MapItem> mis = (List<MapItem>) dsi.get(MapItem.class.getName(),filter, true); 
		assertEquals(1,mis.size());
		DataComparator.assertEquals(mi, mis.get(0), true, true);
		
		
		filter = "id = 1" + mi.getId();
		mis = (List<MapItem>) dsi.get(MapItem.class.getName(),filter, true); 
		assertEquals(0,mis.size());
	}

	@Test
	public void testSearchString()
	{
		///TODO
//		fail("Not yet implemented");
	}

	@Test
	public void testSearchStringFloatFloatInt()
	{
		///TODO
//		fail("Not yet implemented");
	}

	@Test
	public void testProcessStar() throws Exception
	{
		Star s = RandomGenerator.genRandomStar();
		List<Star> fromDb = (List<Star>) dsi.get(Star.class.getName()); 
		assertEquals(0,fromDb.size());
		dsi.processStar(s, DataService.ADD);
		//test add
		fromDb = (List<Star>) dsi.get(Star.class.getName()); 
		assertEquals(1,fromDb.size());
		DataComparator.assertEquals(s, fromDb.get(0), true);
		
		//test update
		s.setNote("UPDATEDNOTE");
		dsi.processStar(s, DataService.UPDATE);
		fromDb = (List<Star>) dsi.get(Star.class.getName()); 
		assertEquals(1,fromDb.size());
		DataComparator.assertEquals(s, fromDb.get(0), true);
		
		//test delete
		dsi.processStar(s, DataService.DELETE);
		fromDb = (List<Star>) dsi.get(Star.class.getName()); 
		assertEquals(0,fromDb.size());
	}

	@Test
	public void testProcessDetail() throws Exception
	{
		MapItem mi = RandomGenerator.genMapItem(false);
		assertEquals(0,dsi.get(MapItem.class.getName()).size());
		dsi.processMapItem(mi, DataService.ADD);
		List<MapItem> mis = (List<MapItem>) dsi.get(MapItem.class.getName()); 
		assertEquals(1,mis.size());
		assertEquals(0, mis.get(0).getDetails().size());
		
		//test add
		Detail d = RandomGenerator.genRandomDetail();		
		dsi.processDetail(d, DataService.ADD, mi.getId());
		assertTrue(d.getId() > 0);
		mis = (List<MapItem>) dsi.get(MapItem.class.getName()); 
		assertEquals(1,mis.size());
		assertEquals(1, mis.get(0).getDetails().size());
		DataComparator.assertEquals(d, mis.get(0).getDetails().get(0), true);
		
		//test update
		d.setWhat("What 1");
		d.setDetail("Detail 1");
		dsi.processDetail(d, DataService.UPDATE, null);
		mis = (List<MapItem>) dsi.get(MapItem.class.getName()); 
		assertEquals(1,mis.size());
		assertEquals(1, mis.get(0).getDetails().size());
		DataComparator.assertEquals(d, mis.get(0).getDetails().get(0), true);
		
		//test Delete
		dsi.processDetail(d, DataService.DELETE, null);
		mis = (List<MapItem>) dsi.get(MapItem.class.getName()); 
		assertEquals(1,mis.size());
		assertEquals(0, mis.get(0).getDetails().size());
	}

	@Test
	public void testProcessString() throws Exception
	{
		String s = AppUtils.generateRandomString();
		List<String> fromDb = dsi.getMapItemTypes();
		assertEquals(0, fromDb.size());
		//add
		dsi.processString(s, DataService.TYPE_MAPITEM_TYPE, DataService.ADD);
		fromDb = dsi.getMapItemTypes();
		assertEquals(1, fromDb.size());
		assertEquals(s, fromDb.get(0));
		
		//update
		try
		{
			dsi.processString(s, DataService.TYPE_MAPITEM_TYPE, DataService.UPDATE);
			fail("should throw exception");
		}
		catch(Exception e){}
		//delete
		dsi.processString(s, DataService.TYPE_MAPITEM_TYPE, DataService.DELETE);
		fromDb = dsi.getMapItemTypes();
		assertEquals(0, fromDb.size());
	}

}
