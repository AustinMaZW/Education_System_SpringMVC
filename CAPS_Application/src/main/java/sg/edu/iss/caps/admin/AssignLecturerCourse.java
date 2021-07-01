package sg.edu.iss.caps.admin;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sg.edu.iss.caps.course.Course;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AssignLecturerCourse {
	private List<Course> courses;
}
