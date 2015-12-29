package comp3350.srsys.persistence;

import java.util.ArrayList;
import java.util.List;

import comp3350.srsys.objects.Student;
import comp3350.srsys.objects.Course;
import comp3350.srsys.objects.SC;

public interface DataAccess
{
	public void open(String string);

	public void close();

	public String getStudentSequential(List<Student> studentResult);

	public ArrayList<Student> getStudentRandom(Student student);

	public String insertStudent(Student student);

	public String updateStudent(Student student);

	public String deleteStudent(Student student);

	public String getCourseSequential(List<Course> courseResult);

	public ArrayList<Course> getCourseRandom(Course course);

	public String insertCourse(Course course);

	public String updateCourse(Course course);

	public String deleteCourse(Course course);

	public ArrayList<SC> getSC(SC sc);

	public ArrayList<SC> getCS(SC sc);
}
