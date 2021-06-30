package sg.edu.iss.caps.lecturer;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import sg.edu.iss.caps.student.Student;

@Component
public class MapperUtility {

	public Student buildStudentObj(Integer id, String username, String firstname, String lastname, LocalDate matriculationDate, Double gpa) {
		Student student = new Student();
		student.setId(id);
		student.setUsername(username);
		student.setFirstName(firstname);
		student.setLastName(lastname);
		student.setMatriculationDate(matriculationDate);
		student.setGpa(gpa);
		
		return student;
	}
}
