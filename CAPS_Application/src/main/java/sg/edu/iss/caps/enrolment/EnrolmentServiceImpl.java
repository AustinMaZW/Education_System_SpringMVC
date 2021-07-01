package sg.edu.iss.caps.enrolment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.course.Course;
import sg.edu.iss.caps.course.CourseRepository;
import sg.edu.iss.caps.model.Status;
import sg.edu.iss.caps.model.StudentEnrolmentDTO;
import sg.edu.iss.caps.student.Student;
import sg.edu.iss.caps.student.StudentRepository;

//check for unnecessary methods to remove
@Service
public class EnrolmentServiceImpl implements EnrolmentService {
	@Autowired
	EnrolRepository erepo;
	@Autowired
	StudentRepository srepo;
	@Autowired
	CourseRepository crepo;

	@Transactional
	public void openEnrolment(CourseEnrolment enrolment) {
		erepo.save(enrolment);
	}

	@Transactional
	public void closeEnrolment(CourseEnrolment enrolment) {
		enrolment.setStatus(Status.NOTAVAILABLE);
		erepo.save(enrolment);
	}

	@Override
	@Transactional
	public List<CourseEnrolment> findAllEnrolment() {
		List<CourseEnrolment> list = erepo.findAll();
		return list;
	}

	@Override
	@Transactional
	public List<CourseEnrolment> findEnrolmentByStartDate(LocalDate start) {
		List<CourseEnrolment> list = erepo.findAll();
		List<CourseEnrolment> query = new ArrayList<>();
		list.stream().filter(x -> x.getStartDate().isEqual(start)).forEach(x -> query.add(x));
		return query;
	}

	@Override
	@Transactional
	public List<CourseEnrolment> findEnrolmentByEndDate(LocalDate end) {
		List<CourseEnrolment> list = erepo.findAll();
		List<CourseEnrolment> query = new ArrayList<>();
		list.stream().filter(x -> x.getEndDate().isEqual(end)).forEach(x -> query.add(x));
		return query;
	}

	@Override
	@Transactional
	public List<CourseEnrolment> findEnrolmentByDuration(LocalDate start, LocalDate end) {
		List<CourseEnrolment> list = erepo.findAll();
		List<CourseEnrolment> query = new ArrayList<>();
		list.stream()
				.filter(x -> (x.getEndDate().isBefore(end) || x.getEndDate().isEqual(end))
						&& (x.getStartDate().isAfter(start) || x.getStartDate().isEqual(start)))
				.forEach(x -> query.add(x));
		return query;
	}

	@Override
	@Transactional
	public List<CourseEnrolment> findEnrolmentByCourse(Course course) {
		List<CourseEnrolment> list = erepo.findEnrolmentByCourse(course.getId());
		return list;
	}

	@Override
	@Transactional
	public List<CourseEnrolment> findEnrolmentByStudent(Student student) {
		Student stu = srepo.findById(student.getId()).get();
		List<CourseEnrolment> enroList = new ArrayList<>(stu.getGrades().keySet());
		return enroList;
	}

	@Override
	@Transactional
	public boolean UpdateEnrolment(CourseEnrolment enrol) {
		CourseEnrolment curr = erepo.findById(enrol.getId()).get();
		if (curr == null)
			return false;
		int numOfStudents = findStudentsByEnrol(curr).size();

		curr.setCapacity(enrol.getCapacity());
		curr.setCourse(crepo.findCourseByName(enrol.getCourse().getName()));
		curr.setEndDate(enrol.getEndDate());
		curr.setStartDate(enrol.getStartDate());

		if ((numOfStudents == curr.getCapacity())) {
			curr.setStatus(Status.NOTAVAILABLE);
		} else if ((numOfStudents < curr.getCapacity())) {
			curr.setStatus(enrol.getStatus());
		} else if ((numOfStudents > curr.getCapacity())) {
			return false;
		}

		if (erepo.save(curr) == null)
			return false;
		return true;

	}

	@Override
	@Transactional
	public boolean CreateEnrolment(CourseEnrolment enrol) {
		if (erepo.save(enrol) == null)
			return false;
		return true;

	}

	@Override
	@Transactional
	public void DeleteEnrolment(int id) {
		CourseEnrolment enrol = erepo.findById(id).get();
		erepo.delete(enrol);
	}

	@Override
	@Transactional
	public CourseEnrolment findEnrolmentById(int id) {
		return erepo.findCourseEnrolmentById(id);
	}

	@Override
	@Transactional
	public boolean cancelEnrol(int id) {
		CourseEnrolment enrol = erepo.findById(id).get();
		if (enrol.getStatus() == Status.AVAILABLE)
			return false;
		enrol.setStatus(Status.NOTAVAILABLE);
		erepo.save(enrol);
		return true;

	}

	@Override
	@Transactional
	public List<CourseEnrolment> findEnrolmentByCourseName(String queryString) {
		List<CourseEnrolment> list = erepo.findEnrolmentByCourseName(queryString);
		return list;
	}

	@Override
	@Transactional
	public void cancelEnrol(CourseEnrolment enrol) {
		enrol.setStatus(Status.NOTAVAILABLE);
		erepo.save(enrol);
	}

	@Override
	@Transactional
	public List<Student> findStudentsByEnrol(CourseEnrolment enrol) {
		List<Student> stus = srepo.findAll();
		List<Student> newList = new ArrayList<>();
		stus.stream().forEach(x -> {
			if (x.getGrades().keySet().contains(enrol)) {
				newList.add(x);
			}
		});
		return newList;
	}

	@Override
	@Transactional
	public List<StudentEnrolmentDTO> getStudentsByEnrolment(int enrolId) {
		return erepo.findStudentsByEnrolment(enrolId);
	}

	@Override
	@Transactional
	public List<StudentEnrolmentDTO> getStudentsByCourse(int courseId) {
		// TODO Auto-generated method stub
		return null;
	}

}
