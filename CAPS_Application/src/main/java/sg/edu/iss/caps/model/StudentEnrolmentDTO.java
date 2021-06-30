package sg.edu.iss.caps.model;

import org.springframework.beans.factory.annotation.Value;

import sg.edu.iss.caps.student.Student;

public interface StudentEnrolmentDTO {

	@Value("#{@mapperUtility.buildStudentObj(target.id, target.username, target.firstname, target.lastname, target.matriculationDate, target.gpa)}")
	Student getStudent();
}