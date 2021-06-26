package sg.edu.iss.caps.admin;

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

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.viewcourse.CourseInterface;

@Controller
@RequestMapping("/admin/course")
public class AdminCourseController {
	@Autowired
	CourseInterface cservice;
	
	public AdminCourseController() {
		//empty constructor
	}
	
	@GetMapping(value="")
	public String listCourse(Model model) {
		List<Course> clist = cservice.listAllCourses();
		Course course = new Course();
		model.addAttribute("clist", clist);
		model.addAttribute("course", course); //used for add course modal in view
		return "course/course";
	
	}
	
	@PostMapping(value="/save")
	public String saveCourse(@ModelAttribute @Valid Course course, BindingResult result) {
		if (result.hasErrors()) { return "course/course";}
		cservice.updateCourse(course);
		return "redirect:/admin/course";
		
	}
	
//	@PostMapping(value="/save")
//	public String saveEditCourse(@Valid Course course, BindingResult result) {
//		if (result.hasErrors()) { return "course/editcourse";}
//		cservice.updateCourse(course);
//		return "redirect:/admin/course";
//	}
	
//	@GetMapping(value="/new")
//	public String addCourse (Model model) {
//		Course course = new Course();
//		model.addAttribute("course", course);
//		return "course/editcourse";
//	}
		
	@GetMapping("/delete/{id}")
	public String deleteMethod(@PathVariable("id") Integer id) {
		Course course = cservice.findCourseById(id);
		cservice.deleteCourse(course);
		return "redirect:/admin/course";
	}
}
