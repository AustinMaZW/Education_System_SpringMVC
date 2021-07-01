package sg.edu.iss.caps.admin;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.caps.course.Course;
import sg.edu.iss.caps.course.CourseInterface;
import sg.edu.iss.caps.lecturer.Lecturer;
import sg.edu.iss.caps.lecturer.LecturerInterface;

@Controller
//@RequestMapping("/admin")
public class ManageLecturerController {
	
	private static final String NEW_LECTURER_FORM = "create-lecturer-form";
	private static final String LECTURER_LIST ="lecturer/list";
	
	@Autowired
//	private ManageLecturerImplementation lservice;
//	
//	@Autowired
//	public void setLecturer(ManageLecturerImplementation ls) {
//
//		this.lservice= ls;
//	}
	
	LecturerInterface lservice;
	
	@Autowired
	CourseInterface cservice;

	@GetMapping("/admin/lecturer/list")
	public String listAllLecturer(Model model){
		List<Lecturer> lecList = lservice.findAllLecturer();
		Lecturer lecturer = new Lecturer();		
		List<Course> cl = cservice.listAllCourses();
		AssignLecturerCourse clist = new AssignLecturerCourse(cl);
		model.addAttribute("lecList",lecList);
		model.addAttribute("lecturer", lecturer);
		model.addAttribute("clist",clist);
		ArrayList<Course> nclist = new ArrayList<Course>();
		model.addAttribute("nclist", nclist);
		return LECTURER_LIST;

	}	
	
	// for non-modal usage
//	@GetMapping("/admin/lecturer/new")
//	public String newLecturerForm(Map <String, Object> model) {
//		Lecturer lecturer = new Lecturer();
//		model.put("lecturer", lecturer);
//		return NEW_LECTURER_FORM;
//	}
	
	
	@PostMapping("/admin/lecturer/save")
	public String saveLecturerForm(@ModelAttribute @Valid Lecturer lecturer, BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/admin/lecturer/list";
		}
		if(lservice.isNewLecturer(lecturer.getId())) {
			lservice.updateLecturer(lecturer);
		}
		else
			lservice.createLecturer(lecturer);
		return ("redirect:/admin/"+ LECTURER_LIST);
	}

//Change of status which might not be using
//
//	@GetMapping("/admin/change-status/{id}")
//	public String removeLecturer(@PathVariable("id") int id) {
//		lservice.removeLecturerById(id);
//		return ("redirect:/admin/"+ LECTURER_LIST);
//	}
	

	// for non-modal usage
//	@GetMapping("/admin/lecturer-edit/{id}")
//	public String updateLecturer(@PathVariable("id") int id, Model model) {
//		Lecturer lecturer = lservice.findLecturerById(id); // possible lecturer return null
//		model.addAttribute("lecturer",lecturer);
//		return NEW_LECTURER_FORM;
//	}
	
//	@PostMapping("/admin/lecturer-edit")
//	public String updateLecturerForm(@ModelAttribute @Valid Lecturer lecturer, BindingResult result) {
//		if(result.hasErrors()) {
//			return "redirect:/admin/lecturer/list";
//		}
//		lservice.updateLecturer(lecturer);
//		return ("redirect:/admin/" + LECTURER_LIST);
//	}

	@GetMapping("/admin/lecturer-delete/{id}")
	public String deleteLecturer(@PathVariable("id") int id) {
		lservice.deleteLecturerById(id);
		return ("redirect:/admin/"+LECTURER_LIST);
	}
	
	@RequestMapping("/admin/lecturer/courses/assign")
	public String assignCourses(@ModelAttribute @Valid AssignLecturerCourse clist, @RequestParam("id") int lecturerId, BindingResult result) {
		if(result.hasErrors()) {
			return "redirect:/admin";
		}
		lservice.assignCourse(clist.getCourses(), lecturerId);
		return ("redirect:/admin/"+LECTURER_LIST);
	}
}
