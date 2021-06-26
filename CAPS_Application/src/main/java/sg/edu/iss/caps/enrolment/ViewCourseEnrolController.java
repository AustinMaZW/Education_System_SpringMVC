package sg.edu.iss.caps.enrolment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseEnrolment;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.student.StudentService;
import sg.edu.iss.caps.viewcourse.CourseInterface;

@Controller
@RequestMapping("/courseview")
public class ViewCourseEnrolController {
	@Autowired
	EnrolmentService eservice;
	@Autowired
	StudentService sservice;
	@Autowired
	CourseInterface cservice;
	private Student stu;

	@RequestMapping("/list")
	public String viewList(Model model) {
		userName();
		Map<CourseEnrolment, Double> grades = stu.getGrades();
		model.addAttribute("grades", grades);
		model.addAttribute("func", "viewList");
//		return "course-enrol-list";
		return "CourseViewEnrolmentList";
	}

	@RequestMapping("/list/enrol")
	public String enrolList(Model model) {
		userName();
		List<CourseEnrolment> allEnrols = eservice.findAllEnrolment();
		allEnrols = validList(allEnrols, this.stu);
		model.addAttribute("validEnrol", allEnrols);
		model.addAttribute("func", "enrolList");
//		return "enrolList";
		return "CourseViewEnrolmentList";
	}

	@RequestMapping("/add/{id}")
	public String takeEnrol(@PathVariable("id") int id, Model model) {
		userName();
		CourseEnrolment newEnrol = eservice.findEnrolmentById(id);
		sservice.setEnrol(newEnrol, stu);
		return "redirect:/courseview/list/enrol";
	}

	@RequestMapping("/cancel/{id}")
	public String cancel(@PathVariable("id") int id) {
		userName();
		CourseEnrolment enrol = eservice.findEnrolmentById(id);
		sservice.cancel(stu, enrol);
		return "redirect:/courseview/list";

	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@RequestParam(value = "queryString") String queryString, Model model) {
		userName();
		System.out.println(queryString);
		List<CourseEnrolment> list = eservice.findEnrolmentByCourseName(queryString);
		list = validList(list, this.stu);
		model.addAttribute("validEnrol", list);
		model.addAttribute("func", "search");
//		return "enrolList";
		return "CourseViewEnrolmentList";
	}

	@RequestMapping("/courselist")
	public String courseList(Model model) {
		userName();
		List<Course> cList = cservice.listAllCourses();
		model.addAttribute("courseList", cList);
		model.addAttribute("func", "courseList");
//		return "course-list";
		return "CourseViewEnrolmentList";
	}

	@RequestMapping(value = "/courselist/enrolss/{id}", method = RequestMethod.GET)
	public String courseLists(@PathVariable("id") int id, Model model) {
		userName();
		System.out.println(id);
		Course course = cservice.findCourseById(id);
		ArrayList<CourseEnrolment> eList = (ArrayList<CourseEnrolment>) eservice.findEnrolmentByCourse(course);
		eList = (ArrayList<CourseEnrolment>) validList(eList, this.stu);
		model.addAttribute("validEnrol", eList);
		model.addAttribute("func", "courseLists");
//		return "enrolList";
		return "CourseViewEnrolmentList";
	}

	private void userName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		this.stu = sservice.findStudentByUsername(name);
	}

	private List<CourseEnrolment> validList(List<CourseEnrolment> list, Student _stu) {
		List<CourseEnrolment> enrols = new ArrayList<CourseEnrolment>(stu.getGrades().keySet());
		enrols.stream().forEach(x -> {
			if (list.contains(x)) {
				list.remove(x);
			}
		});
		return list;
	}
}
