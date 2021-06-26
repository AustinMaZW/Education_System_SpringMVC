package sg.edu.iss.caps.lecturer;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Lecturer;

@Controller
@RequestMapping("/admin")
// maybe this should go under Admin controller.
// public class AdminController {
public class LecturerController {
	
	private static final String NEW_LECTURER_FORM = "create-lecturer-form";
	private static final String LECTURER_LIST ="lecturer-list";
	
	@Autowired
	private LecturerImplementation lservice;
	
	@Autowired
	public void setLecturer(LecturerImplementation ls) {
		this.lservice= ls;
	}
	
	
	@GetMapping("/list")
	public String listAllLecturer(Model model){
		List<Lecturer> lecList = lservice.findAllLecturer();
		model.addAttribute("lecList",lecList);
		return LECTURER_LIST;
	}	
	
	@GetMapping("/new-lecturer")
	public String newLecturerForm(Map <String, Object> model) {
		Lecturer lecturer = new Lecturer();
		model.put("lecturer", lecturer);
		return NEW_LECTURER_FORM;
	}
	
	
	@PostMapping("/new-lecturer")
	public String saveLecturerForm(@Valid Lecturer lecturer, BindingResult result) {
		if(result.hasErrors()) {
			return NEW_LECTURER_FORM;
		}
		else {
			lservice.createLecturer(lecturer);
			return "redirect:/admin/list";
		}
	}

	
	@GetMapping("/delete")
	public void deleteLecturer(Lecturer lecturer) {
		lservice.deleteLecturer(lecturer);

	}
	
	@GetMapping("/edit")
	public void updateLecturer(Lecturer lecturer) {

	}
	
	
}
