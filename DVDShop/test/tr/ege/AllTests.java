package tr.ege;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for tr.efe.ege");
		//$JUnit-BEGIN$
		suite.addTestSuite(PersonelTest.class);
		suite.addTestSuite(MusteriTest.class);
		suite.addTestSuite(DVDTest.class);
		suite.addTestSuite(ManagerFactoryTest.class);
		//$JUnit-END$
		return suite;
	}

}
