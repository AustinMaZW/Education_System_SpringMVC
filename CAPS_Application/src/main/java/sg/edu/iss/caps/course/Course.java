package sg.edu.iss.caps.course;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sg.edu.iss.caps.enrolment.CourseEnrolment;
import sg.edu.iss.caps.lecturer.Lecturer;

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
	@OneToMany(mappedBy = "course")
	private List<CourseEnrolment> enrols;
	@ManyToMany(mappedBy = "courses")
	private List<Lecturer> lectures;
	private String modularcredits;

	public Course(String name, String description, String category, String modularcredits) {
		super();
		this.name = name;
		this.description = description;
		this.category = category;
		this.modularcredits = modularcredits;
	}
}