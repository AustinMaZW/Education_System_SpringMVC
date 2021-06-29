package sg.edu.iss.caps.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public Course findCourseById(int id) {
		return crepo.findCourseById(id);
	}

	@Override
	public ArrayList<String> findAllCourseNames() {
		List<Course> courses = crepo.findAll();
		ArrayList<String> names = new ArrayList<String>();
		for (Iterator<Course> iterator = courses.iterator(); iterator.hasNext();) {
			Course course = (Course) iterator.next();
			names.add(course.getName());
		}
		return names;
	}

	@Override
	public List<Course> findCoursesByName(String name) {

		return crepo.findCoursesByName(name);
	}

}
