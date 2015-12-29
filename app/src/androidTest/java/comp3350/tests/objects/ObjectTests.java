package comp3350.tests.objects;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ObjectTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Object tests");
        suite.addTestSuite(CourseTest.class);
        suite.addTestSuite(StudentTest.class);
        suite.addTestSuite(SCTest.class);
        return suite;
    }
}
