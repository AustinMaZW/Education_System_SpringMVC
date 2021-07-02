package sg.edu.iss.caps.admin;

import java.util.List;

import sg.edu.iss.caps.student.Student;

public interface AdminInterface {
//	public void createAdmin(Admin admin);
//	public void updateAdmin(Admin admin);
//	public void deleteAdmin(Admin admin);
	
	public List<Student> listAllStudents();
	public Student saveStudent(Student student);
	public Student getStudentById(Integer id);
	public void deleteStudent(Student student);
	
}
