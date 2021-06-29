package sg.edu.iss.caps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import sg.edu.iss.caps.admin.Admin;
import sg.edu.iss.caps.admin.AdminRepository;
import sg.edu.iss.caps.course.Course;
import sg.edu.iss.caps.course.CourseRepository;
import sg.edu.iss.caps.lecturer.Lecturer;
import sg.edu.iss.caps.lecturer.LecturerRepository;
import sg.edu.iss.caps.student.Student;
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

//	@Bean
//	CommandLineRunner runner() {
//		return args -> {
//			// dummy admin account
//			String encryptedpwd = PasswordEncoder().encode("Admin");
//			Admin a = new Admin("Admin", encryptedpwd, null, "Admin", "Admin");
//			arepo.save(a);
//
//			// student account
//			Student s = new Student(null, 0, "Austin", PasswordEncoder().encode("Austin"), null, "Austin", "Ma");
//			srepo.save(s);
//
//			// lecturer account
//			Lecturer l = new Lecturer(null, null, null, "Suria", PasswordEncoder().encode("Suria"), null, "Suria",
//					"forgot");
//			lrepo.save(l);
//
//			// add course demo data
//			Course fopcs = new Course("FOPCS", "fundamental of C#", "Programming");
//			Course oop = new Course("OOP", "Object Oriented Programming", "Programming");
//			Course adlc1 = new Course("ADLC1", "design part 1", "System Analysis");
//			Course adlc2 = new Course("ADLC2", "design part 2", "System Analysis");
//			crepo.save(fopcs);
//			crepo.save(oop);
//			crepo.save(adlc1);
//			crepo.save(adlc2);
//
//		};
//	}
//
//	// dummy method for adding dummy data, will remove once added CRUD for managing
//	// admin, student and lecturer
//	@Bean
//	public PasswordEncoder PasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
