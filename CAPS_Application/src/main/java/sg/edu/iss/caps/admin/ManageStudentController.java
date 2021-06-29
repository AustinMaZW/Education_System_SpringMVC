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
		model.addAttribute("stdlist", stdlist);
		return "students";
	
	}
	
	@GetMapping(value="/std/form")
	public String studentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "student-form";
	}
	
	@PostMapping(value="/std/save")
	public String saveStudent(@Valid Student student, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			if (student.getId() != 0)
				model.addAttribute("mode", "edit");
			return "student-form";
		}
		System.out.println("save student");
		System.out.println(student);
		Student savedStudent = aservice.saveStudent(student);
		System.out.println(savedStudent);
		return "redirect:/admin/std/list";
	}
	
	@GetMapping(value="/std/edit/{id}")
	public String editStudentForm(Model model, @PathVariable("id") Integer id) {
		System.out.println("edit student " + id);
		System.out.println(aservice.getStudentById(id));
		model.addAttribute("student", aservice.getStudentById(id));
		model.addAttribute("mode", "edit");
		return "student-form";
	}

	@GetMapping(value="/std/delete/{id}")
	public String deleteStudent(@PathVariable("id") Integer id) {
		System.out.println("delete student " + id);
		Student student = aservice.getStudentById(id);
		aservice.deleteStudent(student);
		return "redirect:/admin/std/list";
	}
}
