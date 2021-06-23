package sg.edu.iss.caps.viewcourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.iss.caps.model.Course;

public class CourseImplementation implements CourseInterface {

	@Autowired
	CourseRepository crrepo;
	@Override
	public void createCourse(Course course) {
		// TODO Auto-generated method stub
		crrepo.save(course);

	}

	@Override
	public List<Course> listAllCourses() {
		// TODO Auto-generated method stub
		return crrepo.findAll();
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
		crrepo.save(course);
	}

	@Override
	public void deleteCourse(Course course) {
		// TODO Auto-generated method stub
		crrepo.delete(course);
	}

	@Override
	public Course findCourseByName(String name) {
		// TODO Auto-generated method stub
		return crrepo.findCourseByName(name);
	}

}
