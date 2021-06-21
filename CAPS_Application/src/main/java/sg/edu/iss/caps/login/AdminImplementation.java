package sg.edu.iss.caps.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminImplementation implements AdminInterface{
	@Autowired
	AdminRepository arepo;
	
	public void createAdmin(Admin admin) {
		arepo.save(admin);
	}
	
	public void updateAdmin(Admin admin) {
		arepo.save(admin);
	}
	
	public void deleteAdmin(Admin admin) {
		arepo.delete(admin);
	}
	public boolean authenticate(Admin admin) {
		Admin fromDB = arepo.findAdminByUsernameAndPassword(admin.getUsername(), admin.getPassword());
		if (fromDB.getUsername().equals(admin.getUsername()) && 
				fromDB.getPassword().equals(admin.getPassword())){
			return true;
		}
		return false;
	}
}
