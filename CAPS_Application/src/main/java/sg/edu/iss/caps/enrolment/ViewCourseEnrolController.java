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
	private Student stu;

	@RequestMapping("/list")
	public String viewList(Model model) {
		userName();
		Map<CourseEnrolment, Double> grades = stu.getGrades();
		model.addAttribute("grades", grades);
		return "course-enrol-list";
	}

	@RequestMapping("/list/enrol")
	public String enrolList(Model model) {
		userName();
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
		userName();
		CourseEnrolment newEnrol = eservice.findEnrolmentById(id);
		sservice.setEnrol(newEnrol, stu);
		return "redirect:/courseview/list/enrol";
	}

	@RequestMapping("/cancel/{id}")
	private String cancel(@PathVariable("id") int id) {
		userName();
		CourseEnrolment enrol = eservice.findEnrolmentById(id);
		sservice.cancel(stu, enrol);
		return "redirect:/courseview/list";

	}

	public void userName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		this.stu = sservice.findStudentByUsername(name);
	}
}
