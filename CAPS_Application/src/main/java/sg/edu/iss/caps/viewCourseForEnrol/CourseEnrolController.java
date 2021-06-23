package sg.edu.iss.caps.viewCourseForEnrol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.CourseEnrolment;
import sg.edu.iss.caps.viewEnrolment.CourseRepository;
import sg.edu.iss.caps.viewEnrolment.EnrolmentServiceImpl;

@Controller
@RequestMapping()
public class CourseEnrolController {
	@Autowired
	EnrolmentServiceImpl eservice;
	@Autowired
	CourseRepository crepo;

	@RequestMapping()
	public String listEnrol(Model model) {
		List<CourseEnrolment> list = eservice.findAllEnrolment();
		model.addAttribute("enrols", list);
		return "enrolments";
	}
}
