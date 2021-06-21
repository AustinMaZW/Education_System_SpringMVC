package sg.edu.iss.caps.login;

import javax.persistence.Entity;

import sg.edu.iss.caps.model.User;

@Entity
public class Admin extends User{

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String username, String password, String sessionId, String firstName, String lastName) {
		super(username, password, sessionId, firstName, lastName);
		// TODO Auto-generated constructor stub
	}

}
