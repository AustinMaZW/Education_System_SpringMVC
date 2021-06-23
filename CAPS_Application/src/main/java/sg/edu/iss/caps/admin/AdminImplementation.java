package sg.edu.iss.caps.admin;

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
	
	//old code
//	public boolean authenticate(Admin admin) {
//		Admin fromDB = arepo.findAdminByUsernameAndPassword(admin.getUsername(), admin.getPassword());
//		if (fromDB!=null){
//			return true;
//		}
//		return false;
//	}
}
