package comp3350.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import comp3350.tests.objects.MessageTest;
import comp3350.tests.business.InventoryTest;

public class AllTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();
        return suite;
    }

    private static void testObjects()
    {
        suite.addTestSuite(MessageTest.class);
        suite.addTestSuite(InventoryTest.class);
    }

    private static void testBusiness()
    {
    	suite.addTestSuite(InventoryTest.class);
    }
}
