package com.scurab.web.drifmaps.shared.datamodel;

public class SearchResult
{
	public enum Type
	{
		MapItem,
		Detail,
		Pros,
		Cons
	}
	
	public Type type;
	public Object searchedItem;
}
