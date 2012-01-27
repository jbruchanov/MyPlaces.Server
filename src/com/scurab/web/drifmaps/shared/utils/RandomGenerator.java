package com.scurab.web.drifmaps.shared.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.scurab.web.drifmaps.shared.datamodel.Detail;
import com.scurab.web.drifmaps.shared.datamodel.MapItem;
import com.scurab.web.drifmaps.shared.datamodel.Star;

/**
 * Genereates random objects
 * position is somewhere in the middle of bohemia
 * @author Joe Scurab
 *
 */
public class RandomGenerator
{
	private static float czMaxY = 50.680797f;
	private static float czMinY = 48.890004f;
	private static float diffY = czMaxY - czMinY;

	private static float czMaxX = 16.253357f;
	private static float czMinX = 13.742981f;
	private static float diffX = czMaxX - czMinX;

	private static Random r = new Random();
	
	public static Star genRandomStar()
	{
		Star s = new Star();
		if(r.nextInt(30) % 3 != 0)
			s.setNote(AppUtils.generateRandomString());
		s.setX(genRandomX());
		s.setY(genRandomY());
		s.setType(r.nextInt(20));
		return s;
	}
	
	public static float genRandomX()
	{
		return czMinX + r.nextFloat() * diffX;
	}
	
	public static float genRandomY()
	{
		return czMinY + r.nextFloat() * diffY;
	}
	
	public static Detail genRandomDetail()
	{
		Detail d = new Detail();
		d.setDetail(AppUtils.generateRandomString());
		d.setWhat(AppUtils.generateRandomString());
		d.setWhen(new Date(System.currentTimeMillis() - r.nextInt(86400 * 1000 *14)));//now - 14days at top
		return d;
	}
	
	private static MapItem genMapItem()
	{
		MapItem mi = new MapItem();
		mi.setAuthor(AppUtils.generateRandomString());
		mi.setContact(AppUtils.generateRandomString());
		mi.setCountry(AppUtils.generateRandomString(3,3).toUpperCase());
		mi.setCity(AppUtils.generateRandomString());
		mi.setName(AppUtils.generateRandomString());
		mi.setStreet(AppUtils.generateRandomString());
		mi.setStreetViewLink(generateRandomStreetViewLink());
		mi.setType(AppUtils.generateRandomString());
		mi.setWeb(AppUtils.generateRandomString());
		mi.setX(genRandomX());
		mi.setY(genRandomY());
		return mi;
	}
	
	private static String generateRandomStreetViewLink()
	{
		
		StringBuilder sb = new StringBuilder();
		sb.append(X + VALUE_SEPARATOR + 60 * r.nextDouble() + ITEM_SEPARATOR);
		sb.append(Y + VALUE_SEPARATOR + 60 * r.nextDouble() + ITEM_SEPARATOR);
		sb.append(YAW + VALUE_SEPARATOR + 60 * r.nextDouble() + ITEM_SEPARATOR);
		sb.append(PITCH + VALUE_SEPARATOR + 60 * r.nextDouble() + ITEM_SEPARATOR);
		sb.append(ZOOM + VALUE_SEPARATOR + r.nextInt(5));
		return sb.toString();
	}
	private static final String VALUE_SEPARATOR = "=";
	private static final String ITEM_SEPARATOR = ";";
	private static final String X = "X";
	private static final String Y = "Y";
	private static final String YAW = "YAW";
	private static final String PITCH = "PITCH";
	private static final String ZOOM = "ZOOM";
	
	public static MapItem genMapItem(boolean deep)
	{
		MapItem mi = genMapItem();
		if(deep)
		{
			int howMany = 1 + r.nextInt(10);
			mi.setDetails(new ArrayList<Detail>());
			for(int i = 0;i<howMany;i++)
				mi.getDetails().add(genRandomDetail());
			howMany = r.nextInt(10);
			mi.setCons(new ArrayList<String>());
			for(int i = 0;i<howMany;i++)
				mi.getCons().add(AppUtils.generateRandomString());
			howMany = r.nextInt(10);
			mi.setPros(new ArrayList<String>());
			for(int i = 0;i<howMany;i++)
				mi.getPros().add(AppUtils.generateRandomString());			
		}
		return mi;
	}
}
