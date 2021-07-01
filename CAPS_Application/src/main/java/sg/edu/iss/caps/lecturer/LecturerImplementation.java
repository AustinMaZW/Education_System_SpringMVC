package sg.edu.iss.caps.lecturer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.course.Course;
import sg.edu.iss.caps.course.CourseRepository;
import sg.edu.iss.caps.model.Status;
import sg.edu.iss.caps.security.SecurityConfig;

@Service
public class LecturerImplementation implements LecturerInterface {

	@Autowired
	LecturerRepository lrepo;
	
	@Autowired
	CourseRepository crepo;

	@Autowired
	SecurityConfig secConfig;

	public String encodePassword(String password) {
		String encryptedPwd = secConfig.getPasswordEncoder().encode(password);
		return encryptedPwd;
	}

	@Override
	public void createLecturer(Lecturer lecturer) {
		// TODO Auto-generated method stub
//		if (lrepo.existsById(lecturer.getId())) {
			lecturer.setPassword(encodePassword(lecturer.getPassword()));
			lrepo.save(lecturer);
//		} else
//			return;
	}

//	@Override
//	public Lecturer findLecturerByFirstName(String firstName) {
//		// TODO Auto-generated method stub
//		return lrepo.findLecturerByFirstName(firstName);
//	}

	@Override
	public List<Lecturer> findAllLecturer() {
		// TODO Auto-generated method stub
		List<Lecturer> lecList = lrepo.findAll();
		return lecList;
	}

	@Override
	public void updateLecturer(Lecturer lecturer) {
		// TODO Auto-generated method stub
		String password = lrepo.findById(lecturer.getId()).get().getPassword();
		lecturer.setPassword(password);
		lrepo.save(lecturer);
	}

//	@Override
//	public Lecturer findLecturerById(int id) {
//		// TODO Auto-generated method stub
//		if (lrepo.existsById(id)) {
//			Lecturer lecturer = lrepo.findById(id).get();
//			return lecturer;
//		} else
//			return null;
//	}

//	@Override
//	public List<Lecturer> findAllAvailableLecturer() {
//		// TODO Auto-generated method stub
//		List<Lecturer> lecList = lrepo.findAll().stream().filter(e -> e.getStatus().equals(Status.AVAILABLE))
//				.collect(Collectors.toList());
//		return lecList;
//	}

	@Override
	public boolean isNewLecturer(int id) {
		// TODO Auto-generated method stub
		return lrepo.existsById(id);
	}

//	@Override
//	public void removeLecturerById(int id) {
//		// TODO Auto-generated method stub
//		Lecturer lecturer = findLecturerById(id);
//
//		if (lecturer.getStatus().equals(Status.AVAILABLE)) {
//			lecturer.setStatus(Status.NOTAVAILABLE);
//			lrepo.save(lecturer);
//		} else {
//			lecturer.setStatus(Status.AVAILABLE);
//			lrepo.save(lecturer);
//		}
//	}

	@Override
	public void deleteLecturerById(int id) {
		// TODO Auto-generated method stub
		lrepo.deleteById(id);
	}

	@Override
	public Lecturer findLecturerByUsername(String username) {
		return lrepo.findByUsername(username);
	}

	@Override
	public void assignCourse(List<Course> courses, int lecturerId) {
		// TODO Auto-generated method stub
		Lecturer lecturer = lrepo.findById(lecturerId).get();
		lecturer.setCourses(courses);		
		lrepo.save(lecturer);
	}

	@Override
	public List<Course> findCoursesByLecturerId(int lecturerId) {
		// TODO Auto-generated method stub
		List<Course> clist = lrepo.findById(lecturerId).get().getCourses();
		
		return clist;
	}


	public List<Lecturer> findLecturersByCourses(Course course) {
		List<Lecturer> allLecturers = findAllLecturer();
		List<Lecturer> lecturerByCourse = new ArrayList<>();
		allLecturers.stream().forEach(x -> {
			x.getCourses().stream().forEach(y->{
				if (y.getId() == course.getId()) {
					lecturerByCourse.add(x);
				}
			});
		});
		return lecturerByCourse;
	}
}
