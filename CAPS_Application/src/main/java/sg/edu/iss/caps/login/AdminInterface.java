package sg.edu.iss.caps.login;

import java.util.List;

public interface AdminInterface {
	public void createAdmin(Admin admin);
	public void updateAdmin(Admin admin);
	public void deleteAdmin(Admin admin);
	public boolean authenticate(Admin admin);
}
