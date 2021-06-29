package sg.edu.iss.caps.lecturer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.enrolment.EnrolmentService;
import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.viewcourse.CourseInterface;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

	@Autowired
	EnrolmentService eservice;
	@Autowired
	CourseInterface cservice;

	@GetMapping(value="/course/stdlist/{id}")
	public String listStudentsByCourse(@PathVariable("id") Integer id, Model model) {
		List<Integer> stdlist = (ArrayList<Integer>) eservice.getStudentsByCourse(id);
		for (Integer sid : stdlist) {
			System.out.println(sid);
		}
//		Course course = cservice.findCourseById(id);
//		model.addAttribute("course", course);
//		model.addAttribute("stdlist", stdlist);
		return "students-of-course";
	
	}
	
}
