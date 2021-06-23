package sg.edu.iss.caps.viewcourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Course;

@Service
public class CourseImplementation implements CourseInterface {

	@Autowired
	CourseRepository crepo;
	@Override
	public void createCourse(Course course) {
		// TODO Auto-generated method stub
		crepo.save(course);
	}

	@Override
	public List<Course> listAllCourses() {
		// TODO Auto-generated method stub
		return crepo.findAll();
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
		crepo.save(course);
	}

	@Override
	public void deleteCourse(Course course) {
		// TODO Auto-generated method stub
		crepo.delete(course);
	}

	@Override
	public Course findCourseByName(String name) {
		// TODO Auto-generated method stub
		return crepo.findCourseByName(name);
	}

}
