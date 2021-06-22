package sg.edu.iss.caps.viewEnrolment;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.caps.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
