package sg.edu.iss.caps.lecturer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.course.Course;
import sg.edu.iss.caps.course.CourseInterface;
import sg.edu.iss.caps.enrolment.CourseEnrolment;
import sg.edu.iss.caps.enrolment.EnrolmentService;

//add authorization for path
@Controller
@RequestMapping("/lecturer/courses")
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
		model.addAttribute("enrols", enrols);
		return "CourseEnrollments";
	}

	private void userName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		this.lecturer = lservice.findLecturerByUsername(name);
	}
}
