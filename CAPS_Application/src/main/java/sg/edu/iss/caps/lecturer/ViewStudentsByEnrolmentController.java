package sg.edu.iss.caps.lecturer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.enrolment.EnrolmentService;
import sg.edu.iss.caps.model.StudentEnrolmentDTO;
import sg.edu.iss.caps.student.Student;
import sg.edu.iss.caps.course.Course;
import sg.edu.iss.caps.course.CourseInterface;

@Controller
@RequestMapping("/lecturer/enrol")
public class ViewStudentsByEnrolmentController {

	@Autowired
	EnrolmentService eservice;
	
	@Autowired
	CourseInterface cservice;

//	@GetMapping(value="/stdlist/{id}")
//	public String listStudentsByCourse(@PathVariable("id") Integer id, Model model) {
//		// retrieve students information and enrolment start date
//		List<StudentEnrolmentDTO> list = eservice.getStudentsByCourse(id);
//
//		// group students by enrolment start date
//		Map<LocalDate, List<Student>> map = list.stream()
//				.collect(Collectors.groupingBy(x->x.getEnrolmentStartDate(), Collectors.mapping(StudentEnrolmentDTO::getStudent, Collectors.toList())));
//		
//		// sort map by the key(enrolment start date)
//		Map<LocalDate, List<Student>> sortedMap = new LinkedHashMap<>();
//		map.entrySet().stream().sorted(Map.Entry.comparingByKey())
//							   .forEachOrdered(e->sortedMap.put(e.getKey(), e.getValue()));
//
//		Course course = cservice.findCourseById(id);
//	
//		model.addAttribute("course", course);
//		model.addAttribute("stdmap", sortedMap);
//		
//		return "students-of-course";
//	}
	
	@GetMapping(value="/stdlist/{id}")
	public String listStudentsByEnrolment(@PathVariable("id") Integer id, Model model) {
		List<StudentEnrolmentDTO> list = eservice.getStudentsByEnrolment(id);
		List<Student> stdlist = new ArrayList<>();
		list.stream().forEach(x -> stdlist.add(x.getStudent()));
		model.addAttribute("stdlist", stdlist);
		
		return "students-of-enrolment";
	}
	
	//austin random test
}
