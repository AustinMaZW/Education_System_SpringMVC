package sg.edu.iss.caps.login;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sg.edu.iss.caps.model.User;

@Controller
public class LoginController {

	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@GetMapping(value ="/login")
	public String login(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth==null || auth instanceof AnonymousAuthenticationToken) {
		User user = new User();
		model.addAttribute("user", user);
		return "login";
		}
		return "index";
	}
	
	@GetMapping("/")
	public String home() {
		return ("index");
	}
	
//	@PostMapping("fail_login")
//	public String handleFailedLogin() {
//		//logic here
//		System.out.println("User has failed to login");
//		
//		return "redirect:/login?error";
//	}
	
	//use @PreAuthorize to set access, below is just test example
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@GetMapping("/testmodule")
//	public String testmodule()  {
//		return ("admin");
//	}
	

}
