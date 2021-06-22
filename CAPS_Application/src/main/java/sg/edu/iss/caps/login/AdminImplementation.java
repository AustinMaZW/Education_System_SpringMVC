package sg.edu.iss.caps.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Admin;

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
		if (fromDB!=null){
			return true;
		}
		return false;
	}
}
