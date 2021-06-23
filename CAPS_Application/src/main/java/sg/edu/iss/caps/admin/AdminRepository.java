package sg.edu.iss.caps.admin;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Integer>{
	public Admin findAdminByUsernameAndPassword(String username, String password);
	public Admin findByUsername(String username);
}
