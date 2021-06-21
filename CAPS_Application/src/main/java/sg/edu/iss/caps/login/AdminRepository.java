package sg.edu.iss.caps.login;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Integer>{
	public Admin findAdminByUsernameAndPassword(String username, String password);
}
