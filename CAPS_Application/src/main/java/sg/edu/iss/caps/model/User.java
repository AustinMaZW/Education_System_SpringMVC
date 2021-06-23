package sg.edu.iss.caps.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int id;
	@NotEmpty
	@Column(nullable = false)
	private String username;
	@NotEmpty
	@Column(nullable = false)
	private String password;
	private String sessionId;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String secondName;
	public User(@NotEmpty String username, @NotEmpty String password, String sessionId, String firstName,
			String secondName) {
		super();
		this.username = username;
		this.password = password;
		this.sessionId = sessionId;
		this.firstName = firstName;
		this.secondName = secondName;
	}

	
}
