package sg.edu.iss.caps.student;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.caps.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	public Student findStudentByUsernameAndPassword(String username, String password);
	public Student findByUsername(String usernamed);
}
