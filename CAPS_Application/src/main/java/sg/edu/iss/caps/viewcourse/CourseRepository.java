package sg.edu.iss.caps.viewcourse;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.caps.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	
	public Course findCourseByName(String name);
	public Course findCourseById(int id);
	public Course findCourseByDescription(String description);
	public Course findCourseByCategory(String Category);

}
