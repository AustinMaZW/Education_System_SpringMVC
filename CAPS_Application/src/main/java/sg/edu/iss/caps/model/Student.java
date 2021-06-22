package sg.edu.iss.caps.model;

import java.time.LocalDate;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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

	@ElementCollection
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"))
	@MapKeyJoinColumn(name = "enrolment_id")
	@Column(name = "grade")
	private Map<CourseEnrolment, Double> grades;

}

// @ManyToMany
// @JoinTable(name = "student_course", joinColumns = @JoinColumn(name =
// "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
// @JoinTable(name = "student_course", joinColumns = @JoinColumn(name =
// "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
//private List<Course> courses_st;