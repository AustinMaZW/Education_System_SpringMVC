package sg.edu.iss.caps.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.iss.caps.model.User;

@Controller
public class LoginController {

	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@RequestMapping(value ="/login")
	public String login(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
//	@PostMapping("fail_login")
//	public String handleFailedLogin() {
//		//logic here
//		System.out.println("User has failed to login");
//		
//		return "redirect:/login?error";
//	}
	
	@GetMapping("/")
	public String home() {
		return ("index");
	}
	
	@GetMapping("/student")
	public String student()  {
		return ("index");
	}
	
	@GetMapping("/lecturer")
	public String lecturer()  {
		return ("lecturer");
	}
	
	@GetMapping("/admin")
	public String admin()  {
		return ("admin");
	}
	
	
	//use @PreAuthorize to set access, below is just test example
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/testmodule")
	public String testmodule()  {
		return ("admin");
	}
	
	//for test demo purpose, below is restricted to admin only because it is mapped under /admin. Check securityconfig for details
	@GetMapping("/admin/test")
	public String admintest()  {
		return ("admin");
	}


//	Old code 
//	@RequestMapping(value = "/authenticate")
//	public String authenticate(@ModelAttribute("user") User user, Model model, HttpSession session) {
//		if(loginservice.authenticate(user)){
//			return "index";
//		}
//		return "login";
//	}
}
