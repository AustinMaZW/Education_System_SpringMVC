package sg.edu.iss.caps.viewEnrolment;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.caps.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
