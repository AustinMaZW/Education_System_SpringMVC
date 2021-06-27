package sg.edu.iss.caps.admin;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Lecturer;

@Controller
//@RequestMapping("/admin")
public class ManageLecturerController {
	
	private static final String NEW_LECTURER_FORM = "create-lecturer-form";
	private static final String LECTURER_LIST ="lecturer-list";
	
	@Autowired
	private ManageLecturerImplementation lservice;
	
	@Autowired
	public void setLecturer(ManageLecturerImplementation ls) {

		this.lservice= ls;
	}
	
	

	@GetMapping("/admin/lecturer-list")
	public String listAllLecturer(Model model){
		List<Lecturer> lecList = lservice.findAllLecturer();
		model.addAttribute("lecList",lecList);
		return LECTURER_LIST;

	}	
	
	@GetMapping("/admin/new-lecturer")
	public String newLecturerForm(Map <String, Object> model) {
		Lecturer lecturer = new Lecturer();
		model.put("lecturer", lecturer);
		return NEW_LECTURER_FORM;
	}
	
	
	@PostMapping("/admin/new-lecturer")
	public String saveLecturerForm(@Valid Lecturer lecturer, BindingResult result) {
		if(result.hasErrors()) {
			return NEW_LECTURER_FORM;
		}
		if(lservice.isNewLecturer(lecturer.getId())) {
			lservice.createLecturer(lecturer);			
		}
		else {
			lservice.updateLecturer(lecturer);
		}
		return ("redirect:/admin/"+ LECTURER_LIST);
	}

//Change of status which might not be in use
//
//	@GetMapping("/admin/change-status/{id}")
//	public String removeLecturer(@PathVariable("id") int id) {
//		lservice.removeLecturerById(id);
//		return ("redirect:/admin/"+ LECTURER_LIST);
//	}
	

	
	@GetMapping("/admin/edit/{id}")
	public String updateLecturer(@PathVariable("id") int id, Model model) {
		Lecturer lecturer = lservice.findLecturerById(id); // possible lecturer return null
		model.addAttribute("lecturer",lecturer);
		return NEW_LECTURER_FORM;
	}
	
	@PostMapping("/admin/edit/{id}")
	public String updateLecturerForm(@Valid Lecturer lecturer, BindingResult result, @PathVariable("id") int id) {
		if(result.hasErrors()) {
			return NEW_LECTURER_FORM;
		}
		else {
			lecturer.setId(id);
			lservice.updateLecturer(lecturer);
			return ("redirect:/admin/" + LECTURER_LIST);
		}
	}
	
	@GetMapping("/admin/delete-lecturer/{id}")
	public String deleteLecturer(@PathVariable("id") int id) {
		lservice.deleteLecturerById(id);
		return ("redirect:/admin/"+LECTURER_LIST);
	}
	
}
