package sg.edu.iss.caps.viewEnrolment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.CourseEnrolment;

public interface EnrolRepository extends JpaRepository<CourseEnrolment, Integer> {
	@Query("select e from CourseEnrolment e where e.course.Id = :id")
	List<CourseEnrolment> findEnrolmentByCourse(@Param("id") int course_id);
}
