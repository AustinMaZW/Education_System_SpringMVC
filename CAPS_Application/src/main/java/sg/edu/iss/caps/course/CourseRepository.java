package sg.edu.iss.caps.course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	public Course findCourseByName(String name);

	public Course findCourseById(int id);

//	public Course findCourseByDescription(String description);

//	public Course findCourseByCategory(String Category);

	@Query("select c from Course c where c.name like %?1%")
	public List<Course> findCoursesByName(String name);
	
	@Query("SELECT c FROM Course c where c.description= :cdescription")
	public Course findCourseByDescription(@Param("cdescription") String cdescription);
}
