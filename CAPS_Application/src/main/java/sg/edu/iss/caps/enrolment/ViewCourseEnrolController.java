package sg.edu.iss.caps.enrolment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.CourseEnrolment;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.student.StudentServiceImpl;

@Controller
@RequestMapping("/courseview")
public class ViewCourseEnrolController {
	@Autowired
	EnrolmentServiceImpl eservice;
	@Autowired
	StudentServiceImpl sservice;

	@RequestMapping("/list")
	public String viewList(Model model) {
		String name = userName();
		Student stu = sservice.findStudentByUsername(name);
		List<CourseEnrolment> enrols = new ArrayList<CourseEnrolment>(stu.getGrades().keySet());
		List<CourseEnrolment> allEnrols = eservice.findAllEnrolment();
		List<CourseEnrolment> validEnrols = new ArrayList<>();
		allEnrols.stream().forEach(x -> {
			enrols.stream().forEach(y -> {
				if (x.getCourse().getName().compareTo(y.getCourse().getName()) == 0)
					validEnrols.add(x);
			});
		});
		model.addAttribute("validEnrols", validEnrols);
		return "course-enrol-list";
	}

	public String userName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		System.out.println(name);
		return name;
	}
}
