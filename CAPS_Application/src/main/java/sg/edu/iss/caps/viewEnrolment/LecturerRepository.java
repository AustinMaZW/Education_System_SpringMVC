package sg.edu.iss.caps.viewEnrolment;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.caps.model.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {

}
