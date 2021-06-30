package sg.edu.iss.caps.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMethod;
import sg.edu.iss.caps.course.Course;
import sg.edu.iss.caps.enrolment.CourseEnrolment;
import sg.edu.iss.caps.security.UserDetailsImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/student")
@NoArgsConstructor
public class StudentController {
	
	@Autowired 
	StudentRepository srepo;
	@Autowired
	StudentService sservice; 
	
	@RequestMapping(value="view")
	public String listGrades(Model model, Authentication authentication) {
		UserDetailsImpl userdetails = (UserDetailsImpl) authentication.getPrincipal();
		Integer id = userdetails.getId();
		Student s = srepo.findById(id).get();
		model.addAttribute("student",sservice.getGradesAlphabet(s));
		
		Double totalCredit = sservice.getMC(s);
		model.addAttribute("totalCredit", totalCredit);
		
		Double CAP = sservice.getCAP(s);
		model.addAttribute("CAP", CAP);
		
		return "student-view-cgpa";
	}
}
