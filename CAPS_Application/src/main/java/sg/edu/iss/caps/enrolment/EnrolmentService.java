package sg.edu.iss.caps.enrolment;

import java.time.LocalDate;
import java.util.List;

import sg.edu.iss.caps.course.Course;
import sg.edu.iss.caps.student.Student;

//check all the methods to remove unnecessary ones
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

	void updateEnrolment(CourseEnrolment enrol);

	void cancelEnrol(CourseEnrolment enrol);

	List<CourseEnrolment> findEnrolmentByCourseName(String queryString);

	List<Student> findStudentsByEnrol(CourseEnrolment enrol);

}
