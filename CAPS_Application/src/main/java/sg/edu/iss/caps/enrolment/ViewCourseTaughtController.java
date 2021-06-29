package sg.edu.iss.caps.enrolment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.lecturer.LecturerInterface;
import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseEnrolment;
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.viewcourse.CourseInterface;

@Controller
@RequestMapping("/lecture/courses")
public class ViewCourseTaughtController {
	@Autowired
	private CourseInterface cservice;
	@Autowired
	private LecturerInterface lservice;
	@Autowired
	private EnrolmentService eservice;

	private Lecturer lecturer;

	@RequestMapping("/list")
	public String courseList(Model model) {
		userName();
		List<Course> courses = this.lecturer.getCourses();
		model.addAttribute("courses", courses);
		return "ViewCourseTaught";
	}

	@RequestMapping("/enrol/{id}")
	public String enrolList(@PathVariable("id") int id, Model model) {
		Course course = cservice.findCourseById(id);
		List<CourseEnrolment> enrols = eservice.findEnrolmentByCourse(course);
		Map<CourseEnrolment, List<Student>> list = new HashMap<CourseEnrolment, List<Student>>();
		enrols.stream().forEach(x -> {
			List<Student> stus = eservice.findStudentsByEnrol(x);
			list.put(x, stus);
		});
		model.addAttribute("enrols", list);
		return "CourseEnrollments";
	}

	private void userName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		this.lecturer = lservice.findLecturerByUsername(name);
	}
}
