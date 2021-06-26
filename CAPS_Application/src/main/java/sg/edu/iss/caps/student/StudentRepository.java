package sg.edu.iss.caps.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	public Student findStudentByUsernameAndPassword(String username, String password);

	public Student findByUsername(String usernamed);

	@Query("select e from Student e where e.firstName = :name")
	public Student findStudentByFirstName(@Param("name") String name);

	@Query("select e from Student e where e.username = :name")
	public Student findStudentByUsername(@Param("name") String name);

}
