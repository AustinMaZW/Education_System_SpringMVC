package sg.edu.iss.caps.login;

import sg.edu.iss.caps.model.Admin;

public interface AdminInterface {
	public void createAdmin(Admin admin);

	public void updateAdmin(Admin admin);

	public void deleteAdmin(Admin admin);

	public boolean authenticate(Admin admin);
}
