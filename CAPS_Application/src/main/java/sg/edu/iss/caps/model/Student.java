package sg.edu.iss.caps.model;

import java.time.LocalDate;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;

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

	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"))
	@MapKeyJoinColumn(name = "enrolment_id")
	@Column(name = "grade")
	private Map<CourseEnrolment, Double> grades;

	public Student(String username, String password, String firstName, String lastName, LocalDate matriculation_date,
			double gpa) {
		super(username, password, firstName, lastName);
		this.matriculation_date = matriculation_date;
		this.gpa = gpa;
	}

}

// @ManyToMany
// @JoinTable(name = "student_course", joinColumns = @JoinColumn(name =
// "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
// @JoinTable(name = "student_course", joinColumns = @JoinColumn(name =
// "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
// private List<Course> courses_st;