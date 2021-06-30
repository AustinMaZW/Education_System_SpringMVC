package sg.edu.iss.caps.lecturer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import sg.edu.iss.caps.course.CourseInterface;

import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.caps.enrolment.CourseEnrolment;
import sg.edu.iss.caps.enrolment.EnrolmentService;
import sg.edu.iss.caps.model.StudentEnrolmentDTO;
import sg.edu.iss.caps.security.UserDetailsImpl;
import sg.edu.iss.caps.student.Student;
import sg.edu.iss.caps.student.StudentService;

@Controller
@RequestMapping("/lecturer/enrol")
public class ViewStudentsByEnrolmentController {

	@Autowired
	EnrolmentService eservice;

	@Autowired
	CourseInterface cservice;

	@Autowired
	StudentService sservice;
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

	@GetMapping(value = "/stdlist/{id}")
	public String listStudentsByEnrolment(@PathVariable("id") Integer id, Model model) {
		CourseEnrolment courseEnrolment = eservice.findEnrolmentById(id);
		List<StudentEnrolmentDTO> list = eservice.getStudentsByEnrolment(id);
		List<Student> stdlist = new ArrayList<>();
		list.stream().forEach(x -> stdlist.add(x.getStudent()));
		Map<Integer, Double> gradelist = new HashMap<>();
		list.stream().forEach(x -> gradelist.put(x.getStudent().getId(), x.getGrade()));

		StudentListWrapper slw = new StudentListWrapper(stdlist, gradelist); // wrapper class for list of students

		model.addAttribute("students", slw);
		model.addAttribute("enrol", courseEnrolment);
		model.addAttribute("course_id", courseEnrolment.getCourse().getId());
		return "/lecturer/ViewStudentEnrolled";
	}

	// Austin test code for manage grades
//	@RequestMapping("/enrol/students/{id}")
//	public String studentList(@PathVariable("id") int id, Model model) {
//		CourseEnrolment courseEnrolment = eservice.findEnrolmentById(id);
//		List<Student> studentList= eservice.findStudentsByEnrol(courseEnrolment);
//		StudentListWrapper slw = new StudentListWrapper(studentList); //wrapper class for list of students
//
//		for (Student s: studentList) {
//			slw.addGrade(s.getId(), s.getGrades().get(courseEnrolment));
//		}
//		model.addAttribute("students", slw); 
//		model.addAttribute("enrol", courseEnrolment);
//		//model.addAttribute("gradesMap", sgw); 
//		return "/lecturer/ViewStudentEnrolled";
//	}

	@PostMapping("/savegrades/{id}")
	public String saveGrades(@ModelAttribute("StudentListWrapper") StudentListWrapper studentList,
			@PathVariable("id") int id) {

		Set<Integer> studentIds = studentList.getGrades().keySet();
		updateGrade(studentList, id, studentIds);
		// add code to check course completion by student
//		studentIds.stream().forEach(x -> {
//			if()
//		})
//		
		Integer enrolId = id;
		return "redirect:/lecturer/enrol/stdlist/" + enrolId.toString();

	}

	private void updateGrade(StudentListWrapper studentList, int id, Set<Integer> studentIds) {
		studentIds.stream().forEach(x -> {
			if (studentList.getGrades().get(x) == null) {
				return;
			}
			sservice.updateGradeByStudentId(x, id, studentList.getGrades().get(x));
		});

	}

	@GetMapping("/allstudents")
	public String getAllStudents(Model model){
		ArrayList<Student> studentList = sservice.findAllStudent();

		model.addAttribute("studentList", studentList);
		return "lecturer-view-all-student";
	}

//	@RequestMapping("/student/{id}")
//	public String getAStudentPerformance(@PathVariable("id") Integer id, Model model) {
//
//		return new ModelAndView ("redirect:/student/view" + studentId.toString();
//	}
// not sure if can return modelandview instead, the code is same as student controller
	@RequestMapping("/student/{id}")
	public String getAStudentPerformance(@PathVariable("id") Integer id, Model model) {
		Student s = sservice.findStudentById(id);

		model.addAttribute("student",sservice.getGradesAlphabet(s));

		Double totalCredit = sservice.getMC(s);
		model.addAttribute("totalCredit", totalCredit);

		Double CAP = sservice.getCAP(s);
		model.addAttribute("CAP", CAP);

		return "student-view-cgpa";
	}
}

