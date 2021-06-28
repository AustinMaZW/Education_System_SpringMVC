package sg.edu.iss.caps.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sg.edu.iss.caps.enrolment.ComPa;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Lecturer extends User implements ComPa {
	private Level jobLevel;
	private String description;
	private Status status;
	@ManyToMany
	@JoinTable(name = "course_lecturer", joinColumns = @JoinColumn(name = "lecturer_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> courses;

	public Lecturer(String username, String password, String firstName, String lastName, Level jobLevel,
			String description, Status status) {
		super(username, password, firstName, lastName);
		this.jobLevel = jobLevel;
		this.description = description;
		this.status = status;
	}

	public Lecturer(Level jobLevel, String description, Status status, @NotEmpty String username,
			@NotEmpty String password, String sessionId, String firstName, String secondName) {
		super(username, password, sessionId, firstName, secondName);
		this.jobLevel = jobLevel;
		this.status = status;
		this.description = description;
		// TODO Auto-generated constructor stub
	}

}
