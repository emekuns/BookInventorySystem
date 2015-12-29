package comp3350.tests.business;

import junit.framework.Test;
import junit.framework.TestSuite;

public class BusinessTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Business tests");
        suite.addTestSuite(CalculateGPATest.class);
        suite.addTestSuite(AccessStudentsTest.class);
        suite.addTestSuite(AccessCoursesTest.class);
        suite.addTestSuite(AccessSCTest.class);
        return suite;
    }
}
