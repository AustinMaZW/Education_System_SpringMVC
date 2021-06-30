package sg.edu.iss.caps.student;

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

import sg.edu.iss.caps.course.Course;
import sg.edu.iss.caps.course.CourseInterface;
import sg.edu.iss.caps.enrolment.ComPa;
import sg.edu.iss.caps.enrolment.CourseEnrolment;
import sg.edu.iss.caps.enrolment.EnrolmentService;
import sg.edu.iss.caps.model.Status;

//add authorization path
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
	private int courseId;

	@RequestMapping("/list")
	public String viewList(Model model) {
		userName();
		Map<CourseEnrolment, Double> grades = stu.getGrades();
		model.addAttribute("grades", grades);
		model.addAttribute("func", "viewList");
//		return "course-enrol-list";
//		return "CourseViewEnrolmentList";
		return "MyCourse";
	}

	@RequestMapping("/list/enrol")
	public String enrolList(Model model) {
		userName();
		List<CourseEnrolment> allEnrols = eservice.findAllEnrolment();
		allEnrols = validList(allEnrols);
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
		//set course enrol status as full if student takes up last slot
		if(eservice.findStudentsByEnrol(newEnrol).size() == newEnrol.getCapacity()) {
			newEnrol.setStatus(Status.FULL);
		}
		Course course = cservice.findCourseById(this.courseId);
		ArrayList<CourseEnrolment> eList = (ArrayList<CourseEnrolment>) eservice.findEnrolmentByCourse(course);
		eList = (ArrayList<CourseEnrolment>) validList(eList);
		model.addAttribute("validEnrol", eList);
//		return "redirect:/courseview/list/enrol";
		return "EnrolmentOfCourse";
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
		list = validList(list);
		model.addAttribute("validEnrol", list);
		model.addAttribute("func", "search");
//		return "enrolList";
		return "CourseViewEnrolmentList";
	}

	@RequestMapping("/courselist")
	public String courseList(Model model) {
		userName();
		List<Course> cList = cservice.listAllCourses();
		cList = RestCourse(cList); // I don't know whether the course should be filtered or enrolment should be
									// filtered.
		model.addAttribute("courseList", cList);
		model.addAttribute("func", "courseList");
		model.addAttribute("keyword", "");
//		return "course-list";
//		return "CourseViewEnrolmentList";
		return "CourseEnrol";
	}

	@RequestMapping(value = "/courselist/enrolss/{id}", method = RequestMethod.GET)
	public String courseLists(@PathVariable("id") int id, Model model) {
		userName();
		this.courseId = id;
		System.out.println(id);
		Course course = cservice.findCourseById(id);
		ArrayList<CourseEnrolment> eList = (ArrayList<CourseEnrolment>) eservice.findEnrolmentByCourse(course);
		eList = (ArrayList<CourseEnrolment>) validList(eList);
		eList = (ArrayList<CourseEnrolment>) isAvailable(eList);
		model.addAttribute("validEnrol", eList);
		model.addAttribute("func", "courseLists");
//		return "enrolList";
		return "EnrolmentOfCourse";
//		return "CourseEnrol";
	}

	@RequestMapping(value = "/course/search", method = RequestMethod.POST)
	public String courseSearch(@RequestParam(value = "queryString") String queryString, Model model) {
		userName();
		System.out.println(queryString);
		List<Course> list = cservice.findCoursesByName(queryString);
		model.addAttribute("courseList", list);
		model.addAttribute("keyword", queryString);
//		return "enrolList";
		return "CourseEnrol";
	}

	private void userName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		this.stu = sservice.findStudentByUsername(name);
	}

	private List<CourseEnrolment> validList(List<CourseEnrolment> list) {
		List<CourseEnrolment> enrols = new ArrayList<CourseEnrolment>(this.stu.getGrades().keySet());
		enrols.stream().forEach(x -> {
			if (list.contains(x)) {
				list.remove(x);
			}
		});
		return list;
	}

	private <T extends ComPa> List<T> isAvailable(List<T> list) {
		List<T> newList = new ArrayList<>();
		list.stream().filter(x -> x.getStatus().compareTo(Status.AVAILABLE) == 0).forEach(x -> newList.add(x));
		return newList;
	}

	private List<Course> RestCourse(List<Course> list) {
		List<Course> stu_course = this.stu.courseList();
		List<Course> courses = new ArrayList<>();
		list.stream().forEach(x -> {
			if (!stu_course.contains(x)) {
				courses.add(x);
			}
		});
		return courses;
	}
}