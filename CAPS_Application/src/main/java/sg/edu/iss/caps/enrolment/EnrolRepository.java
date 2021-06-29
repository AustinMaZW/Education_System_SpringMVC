package sg.edu.iss.caps.enrolment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.StudentEnrolmentDTO;

public interface EnrolRepository extends JpaRepository<CourseEnrolment, Integer> {
	@Query("select e from CourseEnrolment e where e.course.id = :id")
	List<CourseEnrolment> findEnrolmentByCourse(@Param("id") int course_id);

	@Query(value = "select e from CourseEnrolment e where e.course.name like %?1%")
	List<CourseEnrolment> findEnrolmentByCourseName(String querySting);

	public CourseEnrolment findCourseEnrolmentById(int id);

	@Query(value="select s.id as sId, s.username as sUsername, s.first_name as sFirstname, s.last_name as sLastname, s.matriculation_date as sMDate, s.gpa as sGpa, e.start_date as enrolmentStartDate from course_enrolment e, student_course sc, student s where e.course_id = :id and e.id = sc.enrolment_id and sc.student_id = s.id", nativeQuery = true)
	public List<StudentEnrolmentDTO> findStudentsByCourse(@Param("id") int id);

}