package sg.edu.iss.caps.viewEnrolment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.CourseEnrolment;

@Controller
@RequestMapping("/enrolment")
public class EnrolmentController {
	@Autowired
	EnrolmentServiceImpl eservice;

	@RequestMapping("/list")
	public String Enrolments(Model model) {
		List<CourseEnrolment> list = eservice.findAllEnrolment();
		model.addAttribute("enrols", list);
		return "enrolments";

	}
}
