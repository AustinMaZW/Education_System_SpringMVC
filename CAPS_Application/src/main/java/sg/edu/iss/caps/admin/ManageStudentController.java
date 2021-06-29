package sg.edu.iss.caps.admin;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.student.Student;

@Controller
@RequestMapping("/admin")
public class ManageStudentController {

	@Autowired
	AdminInterface aservice;

	@GetMapping(value="/std/list")
	public String listStudent(Model model) {
		List<Student> stdlist = (ArrayList<Student>) aservice.listAllStudents();
		Student student = new Student();
		model.addAttribute("stdlist", stdlist);
		model.addAttribute("student", student);
		return "students";
	
	}
	
	@PostMapping(value="/std/save")
	public String saveStudent(@Valid Student student, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "students";
		}

		aservice.saveStudent(student);
		return "redirect:/admin/std/list";
	}

	@GetMapping(value="/std/delete/{id}")
	public String deleteStudent(@PathVariable("id") Integer id) {
		Student student = aservice.getStudentById(id);
		aservice.deleteStudent(student);
		return "redirect:/admin/std/list";
	}
}
