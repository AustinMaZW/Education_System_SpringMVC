package sg.edu.iss.caps.model;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Admin extends User {

	public Admin(String username, String password, String sessionId, String firstName, String lastName) {
		super(username, password, sessionId, firstName, lastName);
	}

}
