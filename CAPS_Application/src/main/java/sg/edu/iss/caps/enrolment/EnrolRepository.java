package sg.edu.iss.caps.enrolment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.StudentEnrolmentDTO;

public interface EnrolRepository extends JpaRepository<CourseEnrolment, Integer> {
	@Query("select e from CourseEnrolment e where e.course.id = :id")
	List<CourseEnrolment> findEnrolmentByCourse(@Param("id") int course_id);

//	@Query(value = "select e from CourseEnrolment e where e.course.name like %?1%")
//	List<CourseEnrolment> findEnrolmentByCourseName(String querySting);

	public CourseEnrolment findCourseEnrolmentById(int id);

	@Query(value="select s.id as id, s.username as username, s.first_name as firstname, s.last_name as lastname, s.matriculation_date as matriculationDate, s.gpa as gpa, sc.grade as grade from student_course sc, student s where sc.enrolment_id = :id and sc.student_id = s.id", nativeQuery = true)
	public List<StudentEnrolmentDTO> findStudentsByEnrolment(@Param("id") int id);
}