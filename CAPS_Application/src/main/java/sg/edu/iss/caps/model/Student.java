package sg.edu.iss.caps.model;

import java.time.LocalDate;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;
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
@Entity
public class Student extends User {
	private LocalDate matriculation_date;
	private double gpa;

	@ElementCollection
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"))
	@MapKeyJoinColumn(name = "enrolment_id")
	@Column(name = "grade")
	private Map<CourseEnrolment, Double> grades;

	public Student(LocalDate matric_date, double gpa, @NotEmpty String username, @NotEmpty String password, String sessionId, String firstName,
			String secondName) {
		super(username, password, sessionId, firstName, secondName);
		this.matriculation_date = matric_date;
		this.gpa = gpa;
		// TODO Auto-generated constructor stub
	}

	
}

// @ManyToMany
// @JoinTable(name = "student_course", joinColumns = @JoinColumn(name =
// "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
// @JoinTable(name = "student_course", joinColumns = @JoinColumn(name =
// "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
//private List<Course> courses_st;