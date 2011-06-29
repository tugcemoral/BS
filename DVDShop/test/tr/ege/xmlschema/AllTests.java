package tr.ege.xmlschema;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for tr.ege.xmlschema");
		//$JUnit-BEGIN$
		suite.addTestSuite(DvdXMLSchemaTest.class);
		suite.addTestSuite(PersonelXMLSchemaTest.class);
		suite.addTestSuite(MusteriXMLSChemaTest.class);
		suite.addTestSuite(ReadInstanceTest.class);
		//$JUnit-END$
		return suite;
	}

}
