package sg.edu.iss.caps.enrolment;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseEnrolment;
import sg.edu.iss.caps.viewcourse.CourseRepository;

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
		model.addAttribute("func", "Enrolments");
		model.addAttribute("enrol", new CourseEnrolment());
//		return "enrolments";
		return "CourseViewEnrolmentList";
	}

	@RequestMapping("/add")
	public String addEnrolment(Model model) {
		model.addAttribute("enrol", new CourseEnrolment());
		ArrayList<Course> courses = (ArrayList<Course>) crepo.findAll();
		model.addAttribute("courses", courses);
		model.addAttribute("func", "addEnrolment");
//		return "Enrolment-form";
		return "CourseViewEnrolmentList";
	}

	@RequestMapping("/save")
	public String save(@ModelAttribute("enrol") @Valid CourseEnrolment enrol, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors())
			return "Enrolment-form";
		if (enrol.getId() != 0) {
			eservice.UpdateEnrolment(enrol);
			return "redirect:/enrolment/list";
		}
		System.out.println(enrol.getId());
		Course course = crepo.findCourseByName(enrol.getCourse().getName());
		if (eservice.CreateEnrolment(new CourseEnrolment(course, enrol.getStartDate(), enrol.getEndDate(),
				enrol.getCapacity(), enrol.getStatus())))
			return "redirect:/enrolment/list";
		return "forward:/enrolment/list";
	}

	@RequestMapping("/cancel/{id}")
	public String cancel(@PathVariable("id") int id) {
		if (!eservice.cancelEnrol(id)) {
			System.out.println("cannot cancel");
			return "forward:/enrolment/list";
		}
		System.out.println("Successfully canceled");
		return "forward:/enrolment/list";
	}

	@RequestMapping("/remove/{id}")
	public String removeEnrol(@PathVariable("id") int id) {
		eservice.DeleteEnrolment(id);
		return "redirect:/enrolment/list";
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		CourseEnrolment enrol = eservice.findEnrolmentById(id);
		model.addAttribute("enrol", enrol);
		ArrayList<Course> courses = (ArrayList<Course>) crepo.findAll();
		model.addAttribute("courses", courses);
		model.addAttribute("func", "edit");
//		return "Enrolment-form";
		return "CourseViewEnrolmentList";

	}
}
