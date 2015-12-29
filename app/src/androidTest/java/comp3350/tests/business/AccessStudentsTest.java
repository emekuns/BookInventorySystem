package comp3350.tests.business;

import junit.framework.TestCase;

import comp3350.srsys.application.Services;
import comp3350.srsys.application.Main;

import comp3350.srsys.business.AccessStudents;

import comp3350.srsys.objects.Student;

import comp3350.tests.persistence.DataAccessStub;

public class AccessStudentsTest extends TestCase
{
	private static String dbName = Main.dbName;

	public AccessStudentsTest(String arg0)
	{
		super(arg0);
	}

	public void test1()
	{
		AccessStudents as;
		Student student;

		Services.closeDataAccess();

		System.out.println("\nStarting test AccessStudents");

		Services.createDataAccess(new DataAccessStub(dbName));

		student = new Student("100");

		as = new AccessStudents();

		student = as.getSequential();
		assertNotNull(student);
		assertTrue("100".equals(student.getStudentID()));

		Services.closeDataAccess();

		System.out.println("Finished test AccessStudents");
	}
}