package com.scurab.web.drifmaps.client.controls;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.scurab.web.drifmaps.shared.utils.AppUtils;

/**
 * Test if new added items are before add new item and add new item separator
 * @author Joe Scurab
 *
 */
public class NewItemComboBoxTest extends GWTTestCase
{

	

	@Test
	public void testAddNewItem()
	{
		NewItemComboBox combo = new NewItemComboBox();
		assertEquals(combo.getItemCount(),3);//null + item seperator + addnewitem 
		List<String> randomList = new ArrayList<String>();
		for(int i = 0;i<10;i++)
		{
			String s = AppUtils.generateRandomString();
			randomList.add(s);
			combo.onAddNewItem(s);
			assertEquals(combo.getValue(combo.getSelectedIndex()),s);
			assertEquals(combo.getItemCount(),i+4);//null + item seperator + addnewitem + currentaddeditem
		}
		
		
		int count = combo.getItemCount();
		for(int i = 0;i<count;i++)
		{
			String value = combo.getValue(i); 
			GWT.log(value);
			if(i == 0)
			{
				assertEquals(value,NewItemComboBox.NULL_VALUE);
			}
			else if(i < count - 2)
			{
				assertEquals(value,randomList.get(i-1));//offset about first null item!
			}
			else
			{
				assertEquals(value,NewItemComboBox.SEPARATOR_VALUE);
				value = combo.getValue(++i); 
				GWT.log(value);
				assertEquals(value,NewItemComboBox.ASK_FOR_NEW_ITEM_VALUE);
			}
		}
	}
	
	public void testDistinctValues()
	{
		NewItemComboBox combo = new NewItemComboBox();
		assertEquals(combo.getItemCount(),3);//null + item seperator + addnewitem 		
		List<String> randomList = new ArrayList<String>();
		for(int i = 0;i<10;i++)
		{
			String s = AppUtils.generateRandomString();
			randomList.add(s);
			combo.addItem(s);
			assertNull(combo.getLastInsertException()); //should be null after successful insertion
			assertEquals(combo.getItemCount(), i + 4);//null + item seperator + addnewitem + currentaddeditem
			combo.addItem(s);
			assertNotNull(combo.getLastInsertException()); //should be not null, because this value already exists
			assertEquals(combo.getItemCount(), i + 4);//and should have same count of values
		}
	}
	
	public void testDelete()
	{
		String test = "Test";
		NewItemComboBox combo = new NewItemComboBox();
		assertNull(combo.getLastInsertException());
		assertEquals(combo.getItemCount(),3);
		combo.addItem(test);
		assertEquals(combo.getItemCount(),4);
		assertNull(combo.getLastInsertException());
		combo.addItem(test);
		assertEquals(combo.getItemCount(),4);
		assertNotNull(combo.getLastInsertException());
		
		combo.removeItem(1);//new item should be second, behind NULL value
		assertEquals(combo.getItemCount(),3);
		
		assertEquals(combo.getValue(0),NewItemComboBox.NULL_VALUE);
		assertEquals(combo.getValue(1),NewItemComboBox.SEPARATOR_VALUE);
		assertEquals(combo.getValue(2),NewItemComboBox.ASK_FOR_NEW_ITEM_VALUE);
		
		combo.addItem(test);
		assertEquals(combo.getItemCount(),4);
		assertNull(combo.getLastInsertException());
		
		assertEquals(combo.getValue(0),NewItemComboBox.NULL_VALUE);
		assertEquals(combo.getValue(1),test);
		assertEquals(combo.getValue(2),NewItemComboBox.SEPARATOR_VALUE);
		assertEquals(combo.getValue(3),NewItemComboBox.ASK_FOR_NEW_ITEM_VALUE);
	}
	
	public void testSelectSeparatorValue()
	{
		String test = "Test";
		NewItemComboBox combo = new NewItemComboBox();
		combo.onAddNewItem(test);
		for(int i = 0;i<combo.getItemCount();i++)
		{
			if(combo.getValue(i).equals(NewItemComboBox.SEPARATOR_VALUE))
			{
				combo.setSelectedIndex(i);
				break;
			}
		}
		assertEquals(combo.getSelectedIndex(),0);
		
		
	}
	
	public void testSeparatorLength()
	{
		NewItemComboBox combo = new NewItemComboBox();
		int len = combo.getItemText(1).length();//item separator
		int lastMaxLen = 0;
		for(int i = len; i < len*2;i++)
		{
			String s = AppUtils.generateRandomString(i+1, i+1);
			lastMaxLen = s.length();
			combo.onAddNewItem(s);
			assertEquals(combo.getItemText(combo.getItemCount()-2).length(),lastMaxLen);
		}	
		
		combo.onAddNewItem("XXXXX");
		assertEquals(combo.getItemText(combo.getItemCount()-2).length(),lastMaxLen);
	}

	@Override
	public String getModuleName()
	{
		return "com.scurab.web.drifmaps.DrifMaps";
	}
}
