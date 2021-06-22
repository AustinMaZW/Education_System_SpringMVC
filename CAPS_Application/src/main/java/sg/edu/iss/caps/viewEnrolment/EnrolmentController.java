package sg.edu.iss.caps.viewEnrolment;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseEnrolment;

@Controller
@RequestMapping("/enrolment")
public class EnrolmentController {
	@Autowired
	EnrolmentServiceImpl eservice;
	@Autowired
	CourseRepository crepo;

	@RequestMapping("/list")
	public String Enrolments(Model model) {
		List<CourseEnrolment> list = eservice.findAllEnrolment();
		model.addAttribute("enrols", list);
		return "enrolments";
	}

	@RequestMapping("/add")
	public String addEnrolment(Model model) {
		model.addAttribute("enrol", new CourseEnrolment());
		ArrayList<Course> courses = (ArrayList<Course>) crepo.findAll();
		model.addAttribute("courses", courses);
		return "Enrolment-form";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute("enrol") @Valid CourseEnrolment enrol, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors())
			return "Enrolment-form";
		Course course = crepo.findCourseByName(enrol.getCourse().getName());
		if (eservice.CreateEnrolment(new CourseEnrolment(course, enrol.getStartDate(), enrol.getEndDate(),
				enrol.getCapacity(), enrol.getStatus())))
			return "forward:/enrolment/list";
		return "forward:/enrolment/list";
	}
}
