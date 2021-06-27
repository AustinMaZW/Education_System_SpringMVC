package sg.edu.iss.caps.enrolment;

import java.time.LocalDate;
import java.util.List;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseEnrolment;
import sg.edu.iss.caps.model.Student;

public interface EnrolmentService {
	List<CourseEnrolment> findAllEnrolment();

	List<CourseEnrolment> findEnrolmentByStartDate(LocalDate start);

	List<CourseEnrolment> findEnrolmentByEndDate(LocalDate end);

	List<CourseEnrolment> findEnrolmentByDuration(LocalDate start, LocalDate end);

	List<CourseEnrolment> findEnrolmentByCourse(Course course);

	List<CourseEnrolment> findEnrolmentByStudent(Student student);

	CourseEnrolment findEnrolmentById(int id);

	boolean cancelEnrol(int id);

	boolean UpdateEnrolment(CourseEnrolment enrol);

	boolean CreateEnrolment(CourseEnrolment enrol);

	void DeleteEnrolment(int id);

	List<CourseEnrolment> findEnrolmentByCourseName(String queryString);
}
