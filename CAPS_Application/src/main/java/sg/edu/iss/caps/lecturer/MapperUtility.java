package sg.edu.iss.caps.lecturer;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import sg.edu.iss.caps.student.Student;

@Component
public class MapperUtility {

	public Student buildStudentObj(Integer sId, String sUsername, String sFirstname, String sLastname, LocalDate sMDate, Double sGpa) {
		Student student = new Student();
		student.setId(sId);
		student.setUsername(sUsername);
		student.setFirstName(sFirstname);
		student.setFirstName(sLastname);
		student.setMatriculationDate(sMDate);
		student.setGpa(sGpa);
		
		return student;
	}
}
