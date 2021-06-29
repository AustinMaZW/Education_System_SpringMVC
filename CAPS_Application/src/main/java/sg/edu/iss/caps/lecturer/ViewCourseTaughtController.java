package sg.edu.iss.caps.lecturer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.course.Course;
import sg.edu.iss.caps.course.CourseInterface;
import sg.edu.iss.caps.enrolment.CourseEnrolment;
import sg.edu.iss.caps.enrolment.EnrolmentService;
import sg.edu.iss.caps.student.Student;

//add authorization for path
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
	
	//Austin test code for manage grades 
	@RequestMapping("/enrol/students/{id}")
	public String studentList(@PathVariable("id") int id, Model model) {
		CourseEnrolment courseEnrolment = eservice.findEnrolmentById(id);
		List<Student> studentList= eservice.findStudentsByEnrol(courseEnrolment);
		model.addAttribute("students", studentList);
		model.addAttribute("enrol", courseEnrolment);
		return "/lecturer/ViewStudentEnrolled_Austin";
	}
	
	@PostMapping("/enrol/savegrades")
	public String saveGrades(@ModelAttribute("Student") List<Student> studentList) {
		for(Student s: studentList) {
			System.out.println(s.getId());
		}
		return "redirect:/lecture/list";
	}
}
