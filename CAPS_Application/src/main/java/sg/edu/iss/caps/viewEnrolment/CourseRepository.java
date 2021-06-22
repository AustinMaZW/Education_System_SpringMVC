package sg.edu.iss.caps.viewEnrolment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	@Query("select c from Course c where c.name = :name")
	Course findCourseByName(@Param("name") String name);
}
