package sg.edu.iss.caps.course;

import java.util.ArrayList;
import java.util.List;

public interface CourseInterface {
	public void createCourse(Course course);

	public List<Course> listAllCourses();

	public void updateCourse(Course course);

	public void deleteCourse(Course course);

	public Course findCourseByName(String name);

	public Course findCourseById(int id);

	public ArrayList<String> findAllCourseNames();

	public List<Course> findCoursesByName(String name);
}
