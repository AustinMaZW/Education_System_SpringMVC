package sg.edu.iss.caps.model;

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

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String name;
	private String description;
	private String category;
	@OneToMany(mappedBy = "course")
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

// @ManyToMany
// @JoinTable(name = "lecturer_course", joinColumns = @JoinColumn(name =
// "course_id"), inverseJoinColumns = @JoinColumn(name = "lecturer_id"))
// private List<Lecturer> lecturers;
// @ManyToMany
// @JoinTable(name = "student_course", joinColumns = @JoinColumn(name =
// "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
// private List<Student> students;
