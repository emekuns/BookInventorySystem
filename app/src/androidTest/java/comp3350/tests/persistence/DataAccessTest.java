package comp3350.tests.persistence;

import junit.framework.TestCase;

import java.util.ArrayList;

import comp3350.srsys.application.Services;
import comp3350.srsys.application.Main;

import comp3350.srsys.objects.Student;
import comp3350.srsys.objects.Course;
import comp3350.srsys.objects.SC;
import comp3350.srsys.persistence.DataAccess;

public class DataAccessTest extends TestCase
{
	private static String dbName = Main.dbName;
	
	public DataAccessTest(String arg0)
	{
		super(arg0);
	}

	public void testDataAccess()
	{
		DataAccess dataAccess;
		
		Services.closeDataAccess();

		System.out.println("\nStarting Persistence test DataAccess (using stub)");
		
		// Use the following statement to run with the stub database
		dataAccess = (DataAccess) Services.createDataAccess(new DataAccessStub(dbName));
		
		dataAccessTest();

		System.out.println("Finished Persistence test DataAccess (using stub)");
	}
	
	public static void dataAccessTest() {
		DataAccess dataAccess;
		
		ArrayList<Student> students;
		ArrayList<Course> courses;
		ArrayList<SC> scs;
		
		Student student;
		Course course;
		SC sc;
		
		String result;

		dataAccess = (DataAccess) Services.getDataAccess(dbName);

		students = new ArrayList<Student>();
		result = dataAccess.getStudentSequential(students);
		assertNull(result);
		assertNotNull(students);
		assertEquals(4, students.size());
		student = (Student) students.get(0);
		assertEquals("100", student.getStudentID());
		// ...
		
		courses = new ArrayList<Course>();
		result = dataAccess.getCourseSequential(courses);
		assertNull(result);
		assertNotNull(courses);
		assertEquals(4, courses.size());
		course = (Course) courses.get(0);
		assertEquals("COMP3010", course.getCourseID());
		// ...
		
		scs = dataAccess.getSC(new SC("100", "COMP3010"));
		sc = (SC) scs.get(0);
		assertNotNull(sc);
		assertEquals("100", sc.getStudentID());
		assertEquals("COMP3010", sc.getCourseID());

		Services.closeDataAccess();
	}
}