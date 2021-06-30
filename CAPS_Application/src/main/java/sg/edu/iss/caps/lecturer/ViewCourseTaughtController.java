package sg.edu.iss.caps.lecturer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import sg.edu.iss.caps.student.StudentGradeWrapper;
import sg.edu.iss.caps.student.StudentService;

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
	@Autowired
	private StudentService sservice;
	
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
//		Map<CourseEnrolment, List<Student>> list = new HashMap<CourseEnrolment, List<Student>>();
//		enrols.stream().forEach(x -> {
//			List<Student> stus = eservice.findStudentsByEnrol(x);
//			list.put(x, stus);
//		});
//		model.addAttribute("enrols", list);
		model.addAttribute("enrols", enrols);
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
		StudentListWrapper slw = new StudentListWrapper(studentList); //wrapper class for list of students

		for (Student s: studentList) {
			slw.addGrade(s.getId(), s.getGrades().get(courseEnrolment));
		}
		model.addAttribute("students", slw); 
		model.addAttribute("enrol", courseEnrolment);
		//model.addAttribute("gradesMap", sgw); 
		return "/lecturer/ViewStudentEnrolled_Austin";
	}
	
	@PostMapping("/enrol/savegrades/{id}")
	public String saveGrades(@ModelAttribute("StudentListWrapper") StudentListWrapper studentList, 
			@PathVariable("id") int id) {
		
		Set<Integer> studentIds = studentList.getGrades().keySet();
		updateGrade(studentList, id, studentIds);
		//add code to check course completion by student
//		studentIds.stream().forEach(x -> {
//			if()
//		})
//		
		Integer enrolId = id;
		return "redirect:/lecture/courses/enrol/students/" + enrolId.toString();

	}

	private void updateGrade(StudentListWrapper studentList, int id, Set<Integer> studentIds) {
		studentIds.stream().forEach(x -> {
			if (studentList.getGrades().get(x)==null) {return;}
			sservice.updateGradeByStudentId(x, id, studentList.getGrades().get(x));
		});
		
	}
}
