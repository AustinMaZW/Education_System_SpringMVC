package sg.edu.iss.caps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sg.edu.iss.caps.admin.AdminRepository;
import sg.edu.iss.caps.course.CourseRepository;
import sg.edu.iss.caps.lecturer.LecturerRepository;
import sg.edu.iss.caps.student.StudentRepository;

@SpringBootApplication
public class CapsApplication {

	@Autowired
	AdminRepository arepo;
	@Autowired
	StudentRepository srepo;
	@Autowired
	LecturerRepository lrepo;

	@Autowired
	CourseRepository crepo;

	public static void main(String[] args) {
		SpringApplication.run(CapsApplication.class, args);
	}

}
