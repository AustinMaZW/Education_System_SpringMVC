package sg.edu.iss.caps.student;

import java.util.ArrayList;
import java.util.Map;

import javax.mail.MessagingException;

import sg.edu.iss.caps.enrolment.CourseEnrolment;

public interface StudentService {
	public ArrayList<Student> findAllStudent();

	public Student findStudentById(int id);

	public Student findStudentByFirstName(String name);

	public Student findStudentByUsername(String username);

	public boolean setEnrol(CourseEnrolment enrol, Student stu);

	public void cancel(Student stu, CourseEnrolment enrol);

	public Double getCAP(Student stu);

	Map<CourseEnrolment, String> getGradesAlphabet(Student s);

	public Double getMC(Student s);

	public Student updateGradeByStudentId(int studentId, int enrolId, double grade);
	
	public void sendSimpleTextMail();
	
	public void sendMimeMail(Student stu, CourseEnrolment enrol) throws MessagingException;
}
