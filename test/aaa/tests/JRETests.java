package aaa.tests;

import java.io.IOException;

import com.scurab.web.drifmaps.database.DatabaseTest;
import com.scurab.web.drifmaps.server.DataServiceImplTest;
import com.scurab.web.drifmaps.shared.utils.AppUtilsTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class JRETests extends TestSuite
{
	static public Test suite() throws IOException
	{
		TestSuite suite = new TestSuite();
		suite.addTestSuite(DatabaseTest.class);
		suite.addTestSuite(DataServiceImplTest.class);
		suite.addTestSuite(AppUtilsTest.class);
		return suite;
	}
}
