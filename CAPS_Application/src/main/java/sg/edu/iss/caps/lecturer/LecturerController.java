package sg.edu.iss.caps.lecturer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.Lecturer;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {
	
	@Autowired
	LecturerRepository lrepo;
	@Autowired
	LecturerImplementation lecimpl;
		
	@GetMapping("/manage")
	public List<Lecturer> listAllLecturer(){
		return lecimpl.findAllLecturer();
	}	
	
	@GetMapping("/add")
	public void addNewLecturer(Lecturer lecturer) {
		lrepo.save(lecturer);
	}
	
	@GetMapping("/delete")
	public void deleteLecturer(Lecturer lecturer) {
		lrepo.delete(lecturer);
	}
	
	@GetMapping("/edit")
	public void updateLecturer(Lecturer lecturer) {
		lrepo.save(lecturer);
	}
	
	
}
