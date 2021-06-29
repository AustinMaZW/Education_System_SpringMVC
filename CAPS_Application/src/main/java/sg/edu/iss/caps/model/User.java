package sg.edu.iss.caps.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class User {
	public User(String username, String password, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int id;
	@Column(nullable = false)
	@NotBlank(message = "Enter username")
	private String username;
	@Column(nullable = false)
	@Length(min = 5, message = "Password should contain at least 5 digits")
	private String password;
	private String sessionId;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;

	public User(String username, String password, String sessionId, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.sessionId = sessionId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public boolean isNew() {
		return (Integer)this.id==null;
	}

}
