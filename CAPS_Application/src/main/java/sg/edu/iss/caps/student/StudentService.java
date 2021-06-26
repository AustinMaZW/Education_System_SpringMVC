package sg.edu.iss.caps.student;

import java.util.ArrayList;

import sg.edu.iss.caps.model.Student;

public interface StudentService {
	public ArrayList<Student> findAllStudent();

	public Student findStudentById(int id);

	public Student findStudentByFirstName(String name);

	public Student findStudentByUsername(String username);
}
