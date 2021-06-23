package sg.edu.iss.caps.viewcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CourseController {

	@Autowired
	CourseInterface courseinf;
	
	@Autowired
	public void setCourseInterface(CourseInterface ci) {
		this.courseinf = ci;
	}
	
	
}
