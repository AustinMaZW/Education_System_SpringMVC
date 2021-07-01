package sg.edu.iss.caps.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sg.edu.iss.caps.CapsApplication;
import sg.edu.iss.caps.admin.Admin;
import sg.edu.iss.caps.admin.AdminRepository;
import sg.edu.iss.caps.course.Course;
import sg.edu.iss.caps.course.CourseRepository;
import sg.edu.iss.caps.enrolment.CourseEnrolment;
import sg.edu.iss.caps.enrolment.EnrolRepository;
import sg.edu.iss.caps.enrolment.EnrolmentServiceImpl;
import sg.edu.iss.caps.lecturer.Lecturer;
import sg.edu.iss.caps.lecturer.LecturerRepository;
import sg.edu.iss.caps.model.Level;
import sg.edu.iss.caps.model.Status;
import sg.edu.iss.caps.model.StudentCourseStatus;
import sg.edu.iss.caps.student.Student;
import sg.edu.iss.caps.student.StudentRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CapsApplication.class)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataTest {
	@Autowired
	AdminRepository arepo;
	@Autowired
	StudentRepository srepo;
	@Autowired
	CourseRepository crepo;
	@Autowired
	EnrolRepository erepo;
	@Autowired
	LecturerRepository lrepo;
	@Autowired
	EnrolmentServiceImpl eservice;

	@Test
	@Order(0)
	public void test_1() {
		Admin a = new Admin("Admin", PasswordEncoder().encode("Admin"), null, "admin", "admin");
		arepo.save(a);
		List<Course> courseList = new ArrayList<Course>();
//		courseList.add(new Course("C#", "Microsoft", "Coding"));
//		courseList.add(new Course("Java", "Oracle", "Coding"));
//		courseList.add(new Course("Python", "shit", "Coding"));
//		courseList.add(new Course("Project Mangement", "NUS", "Management"));
		courseList.add(new Course("SOFTWARE ANALYSIS AND DESIGN", "SA4101", "", "6.0"));
		courseList.add(new Course("ENTERPRISE SOLUTIONS DESIGN AND DEVELOPMENT", "SA4102", "Coding", "8.0"));
		courseList.add(new Course("DIGITAL PRODUCT MANAGEMENT", "SA4104", "Coding", "4.0"));
		courseList.add(new Course("WEB APPLICATION DEVELOPMENT", "SA4105", "Management", "6.0"));
		courseList.add(new Course("MOBILE APPLICATION DEVELOPMENT", "SA4106", "Management", "8.0"));
		courseList.stream().forEach(x -> crepo.save(x));
		LocalDate mdate = LocalDate.of(2021, 6, 1);
		List<Student> stuList = new ArrayList<>();
		stuList.add(new Student("Austin", PasswordEncoder().encode("123"), "ZiWang", "Ma", mdate, 00));
		stuList.add(new Student("YZ", PasswordEncoder().encode("123"), "YaZhen", "Chua", mdate, 00));
		stuList.add(new Student("LJ", PasswordEncoder().encode("123"), "YeeJean", "Lim", mdate, 00));
		stuList.add(new Student("NG", PasswordEncoder().encode("123"), "CheZaw", "NguMay", mdate, 00));
		stuList.add(new Student("CK", PasswordEncoder().encode("123"), "ChorKian", "Tang", mdate, 00));
		stuList.add(new Student("Yue", PasswordEncoder().encode("123"), "PengCheng", "Yue", mdate, 00));
		stuList.stream().forEach(x -> srepo.save(x));
		List<Lecturer> lecList = new ArrayList<>();
		lecList.add(new Lecturer("TG", PasswordEncoder().encode("123"), "Tin", "Ngu", Level.SENIOR, "Very good teacher",
				Status.AVAILABLE));
		lecList.add(new Lecturer("LF", PasswordEncoder().encode("123"), "Liu", "Fan", Level.SENIOR, "Very good teacher",
				Status.AVAILABLE));
		lecList.add(new Lecturer("SS", PasswordEncoder().encode("123"), "Suria", "Suria", Level.SENIOR,
				"Very good teacher", Status.AVAILABLE));
		lecList.add(new Lecturer("CW", PasswordEncoder().encode("123"), "Cher", "Wa", Level.SENIOR, "Very good teacher",
				Status.AVAILABLE));
		lecList.add(new Lecturer("YK", PasswordEncoder().encode("123"), "YuenKwan", "Chia", Level.SENIOR,
				"Very good teacher", Status.AVAILABLE));
		lecList.add(new Lecturer("LB", PasswordEncoder().encode("123"), "BonKee", "Lee", Level.SENIOR,
				"Very good teacher", Status.AVAILABLE));
		lecList.add(new Lecturer("FF", PasswordEncoder().encode("123"), "Faci", "Faci", Level.SENIOR,
				"Very good teacher", Status.AVAILABLE));
		lecList.stream().forEach(x -> lrepo.save(x));
	}

	@Test
	@Order(1)
	public void test_2() {
		LocalDate start = LocalDate.of(2021, 6, 10);
		LocalDate end = LocalDate.of(2021, 6, 20);
		List<Course> cList = crepo.findAll();
		cList.stream()
				.forEach(x -> erepo.save(new CourseEnrolment(x, start, end, 50, Status.AVAILABLE)));
		List<CourseEnrolment> enrols = erepo.findAll();
		List<Student> students = srepo.findAll();
		students.stream().forEach(x -> {
			Map<CourseEnrolment, Double> score = x.getGrades(); 
			Map<CourseEnrolment, StudentCourseStatus> status = x.getStatus(); //added
			enrols.stream().forEach(y -> score.put(y,(double) Math.round(Math.random()*(51) + 50)));
			enrols.stream().forEach(y -> status.put(y, StudentCourseStatus.ONGOING)); //added
			x.setGrades(score);
			x.setStatus(status); //added
			srepo.save(x);
		});
	}


	@Test
	@Order(2)
	public void test_3() {
		List<Lecturer> lecs = lrepo.findAll();
		List<CourseEnrolment> enrols = erepo.findAll();
		List<Course> cs = crepo.findAll();
		lecs.stream().forEach(x -> {
			x.setCourses(cs);
			lrepo.save(x);
		});
		cs.stream().forEach(x -> {
			x.setEnrols(enrols);
			crepo.save(x);
		});
	}

	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
