package sg.edu.iss.caps.login;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.caps.model.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer>{
	public Admin findAdminByUsernameAndPassword(String username, String password);
}
