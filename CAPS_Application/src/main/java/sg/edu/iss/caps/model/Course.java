package sg.edu.iss.caps.model;

import java.util.List;

import javax.persistence.*;

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
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String category;
	@OneToMany(mappedBy = "course",cascade = {CascadeType.ALL})
	private List<CourseEnrolment> enrols;
	@ManyToMany(mappedBy = "courses")
	private List<Lecturer> lectures;

	public Course(String name, String description, String category) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
	}

}