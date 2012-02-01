package com.scurab.web.drifmaps.shared.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AppUtilsTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testRound()
	{
		double d = 14.123456465465;
		double expected = 14.1;
		assertTrue(expected == AppUtils.round(d, 1));
		
		expected = 14.12;
		assertTrue(expected == AppUtils.round(d, 2));
		
		expected = 14.123;
		assertTrue(expected == AppUtils.round(d, 3));
		
		expected = 14.1235;
		assertTrue(expected == AppUtils.round(d, 4));
		
		expected = 14.12346;
		assertTrue(expected == AppUtils.round(d, 5));
		
		expected = 14.123456;
		assertTrue(expected == AppUtils.round(d, 6));
		
		expected = 14.1234565;
		assertTrue(expected == AppUtils.round(d, 7));
		
		expected = 14.12345647;
		assertTrue(expected == AppUtils.round(d, 8));

		//         14.123456465465;
		expected = 14.123456465;
		assertTrue(AppUtils.round(d, 8) == AppUtils.round(d, 9));
		
		//         14.123456465465;
		expected = 14.1234564655;
		assertTrue(AppUtils.round(d, 8) == AppUtils.round(d, 10));
	}

}
