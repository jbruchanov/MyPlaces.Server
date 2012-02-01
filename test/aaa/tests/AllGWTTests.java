package aaa.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;
import com.scurab.web.drifmaps.client.component.MapControllerTest;
import com.scurab.web.drifmaps.client.controls.NewItemComboBoxTest;
import com.scurab.web.drifmaps.client.presenter.MainViewPresenterAndControllerTest;
import com.scurab.web.drifmaps.client.presenter.MainViewPresenterTest;

public class AllGWTTests extends GWTTestSuite
{
	static public Test suite()
	{
		TestSuite suite = new TestSuite();
		suite.addTestSuite(MapControllerTest.class);
		suite.addTestSuite(NewItemComboBoxTest.class);
		suite.addTestSuite(MainViewPresenterAndControllerTest.class);
		suite.addTestSuite(MainViewPresenterTest.class);
		return suite;
	}
}
