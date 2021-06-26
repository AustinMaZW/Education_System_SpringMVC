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

import sg.edu.iss.caps.model.CourseEnrolment;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.student.StudentService;

@Controller
@RequestMapping("/courseview")
public class ViewCourseEnrolController {
	@Autowired
	EnrolmentService eservice;
	@Autowired
	StudentService sservice;

	@RequestMapping("/list")
	public String viewList(Model model) {
		String name = userName();
		Student stu = sservice.findStudentByUsername(name);
		Map<CourseEnrolment, Double> grades = stu.getGrades();
		model.addAttribute("grades", grades);
		return "course-enrol-list";
	}

	@RequestMapping("/list/enrol")
	public String enrolList(Model model) {
		String name = userName();
		Student stu = sservice.findStudentByUsername(name);
		List<CourseEnrolment> enrols = new ArrayList<CourseEnrolment>(stu.getGrades().keySet());
		List<CourseEnrolment> allEnrols = eservice.findAllEnrolment();
		enrols.stream().forEach(x -> {
			if (allEnrols.contains(x)) {
				allEnrols.remove(x);
			}
		});
		model.addAttribute("validEnrol", allEnrols);
		return "enrolList";
	}

	@RequestMapping("/add/{id}")
	public String takeEnrol(@PathVariable("id") int id, Model model) {
		String name = userName();
		Student stu = sservice.findStudentByUsername(name);
		CourseEnrolment newEnrol = eservice.findEnrolmentById(id);
		Map<CourseEnrolment, Double> newE = stu.getGrades();
		sservice.setEnrol(newEnrol, stu);
		return "redirect:/courseview/list";
	}

	@RequestMapping("/cancel/{id}")
	public String cancel(@PathVariable("id") int id) {
		String name = userName();
		Student stu = sservice.findStudentByUsername(name);
		CourseEnrolment enrol = eservice.findEnrolmentById(id);
		sservice.cancel(stu, enrol);
		return "redirect:/courseview/list";

	}

	public String userName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		System.out.println(name);
		return name;
	}
}
