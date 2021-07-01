package sg.edu.iss.caps.course;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.lecturer.Lecturer;
import sg.edu.iss.caps.lecturer.LecturerInterface;
import sg.edu.iss.caps.lecturer.LecturerRepository;

@Service
public class CourseImplementation implements CourseInterface {

	@Autowired
	CourseRepository crepo;
	@Autowired
	LecturerRepository lrepo;
	@Autowired
	LecturerInterface lservice;
	
//	@Override
//	public void createCourse(Course course) {
//		// TODO Auto-generated method stub
//		crepo.save(course);
//	}

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
	public boolean deleteCourse(Course course) {
		// TODO Auto-generated method stub
		if(course.getEnrols().size()!=0) {return false;}
		List<Lecturer> lecturers = lservice.findLecturersByCourses(course);
		if(lecturers.size()!=0) {
			lecturers.stream().forEach(x -> {
				List<Course> c = new ArrayList<>();
				x.getCourses().stream().forEach(y-> {
					if(y.getId()!=course.getId()) {
						c.add(y);
					}
				});
				x.setCourses(c);
				lrepo.save(x);
			});
		}

		crepo.delete(course);
		return true;
	}

//	@Override
//	public Course findCourseByName(String name) {
//		// TODO Auto-generated method stub
//		return crepo.findCourseByName(name);
//	}

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

	@Override
	public Course findCourseByDescription(String cdescription) {
		// TODO Auto-generated method stub
		Course course = crepo.findCourseByDescription(cdescription);
		return course;
	}

}
