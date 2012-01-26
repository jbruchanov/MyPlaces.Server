package com.scurab.web.drifmaps.client.map;

import com.scurab.web.drifmaps.shared.datamodel.Star;

public class StarOverlay extends MapItemOverlay<Star>
{
	private Star mStar = null;
	public StarOverlay(Star mi)
	{
		super(mi);
		mStar = mi;
	}
}
