package sg.edu.iss.caps.model;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;

import sg.edu.iss.caps.student.Student;

public interface StudentEnrolmentDTO {

	@Value("#{@mapperUtility.buildStudentObj(target.sId, target.sUsername, target.sFirstname, target.sLastname, target.sMDate, target.sGpa)}")
	Student getStudent();

	LocalDate getEnrolmentStartDate();
}