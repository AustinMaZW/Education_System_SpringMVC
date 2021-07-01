package sg.edu.iss.caps.student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyJoinColumn;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sg.edu.iss.caps.course.Course;
import sg.edu.iss.caps.enrolment.CourseEnrolment;
import sg.edu.iss.caps.model.User;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends User {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate matriculationDate;
	private double gpa;

	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"))
	@MapKeyJoinColumn(name = "enrolment_id")
	@Column(name = "grade")
	private Map<CourseEnrolment, Double> grades;

	public Student(String username, String password, String firstName, String lastName, LocalDate matriculationDate,
			double gpa) {
		super(username, password, firstName, lastName);
		this.matriculationDate = matriculationDate;
		this.gpa = gpa;
	}

	public Student(LocalDate matriculationDate, double gpa, @NotEmpty String username, @NotEmpty String password,
			String sessionId, String firstName, String lastName) {
		super(username, password, sessionId, firstName, lastName);
		this.matriculationDate = matriculationDate;
		this.gpa = gpa;
	}

	public List<Course> courseList() {
		List<Course> courses = new ArrayList<>();
		List<CourseEnrolment> enrols = new ArrayList<>(this.grades.keySet());
		enrols.stream().forEach(x -> courses.add(x.getCourse()));
		return courses;
	}

}

// @ManyToMany
// @JoinTable(name = "student_course", joinColumns = @JoinColumn(name =
// "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
// @JoinTable(name = "student_course", joinColumns = @JoinColumn(name =
// "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
// private List<Course> courses_st;