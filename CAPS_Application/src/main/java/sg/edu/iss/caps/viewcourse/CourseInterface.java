package sg.edu.iss.caps.viewcourse;

import java.util.List;

import sg.edu.iss.caps.model.Course;

public interface CourseInterface {
	public void createCourse(Course course);
	public List<Course> listAllCourses();
	public void updateCourse(Course course);
	public void deleteCourse(Course course);
	public void findCourseByName(String name);
	
}
