package sg.edu.iss.caps.admin;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.NoArgsConstructor;
import lombok.ToString;
import sg.edu.iss.caps.model.User;

@ToString
@NoArgsConstructor
@Entity
public class Admin extends User{

	public Admin(@NotEmpty String username, @NotEmpty String password, String firstName,
			String secondName) {
		super(username, password, firstName, secondName);
		// TODO Auto-generated constructor stub
	}


}
