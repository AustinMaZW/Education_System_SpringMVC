package sg.edu.iss.caps.enrolment;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import sg.edu.iss.caps.course.Course;
import sg.edu.iss.caps.model.Status;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CourseEnrolment implements ComPa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Course course;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	private int capacity;
	private Status status;

	// @ManyToMany
	// @JoinTable(name = "student_course", joinColumns = @JoinColumn(name =
	// "enrolment_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	// private List<Student> studentList;
	public CourseEnrolment(Course course, LocalDate startDate, LocalDate endDate, int capacity, Status status) {
		super();
		this.course = course;
		this.startDate = startDate;
		this.endDate = endDate;
		this.capacity = capacity;
		this.status = status;
	}
}
