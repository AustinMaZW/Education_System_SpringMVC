package sg.edu.iss.caps.course;

import java.util.ArrayList;
import java.util.List;

public interface CourseInterface {
	public void createCourse(Course course);

	public List<Course> listAllCourses();//used by ViewCourseEnrolController ManageCourseController

	public void updateCourse(Course course);// ManageCourseController

	public void deleteCourse(Course course);// ManageCourseController

	public Course findCourseByName(String name);// ViewCourseEnrolController

	public Course findCourseById(int id); // ViewCourseTaughtController ViewCourseEnrolController LecturerController

	public ArrayList<String> findAllCourseNames(); // ManageEnrolmentController 

	public List<Course> findCoursesByName(String name); // ViewCourseEnrolController
}
