package sg.edu.iss.caps.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@Autowired
	AdminInterface aservice;

	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(path ="/login")
	public String login(Model model) {
		return "login";
	}
}
