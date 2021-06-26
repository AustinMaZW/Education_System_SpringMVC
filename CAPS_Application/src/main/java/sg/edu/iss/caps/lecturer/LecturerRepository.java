package sg.edu.iss.caps.lecturer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.iss.caps.model.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer>{
	
	Lecturer findLecturerByUsernameAndPassword(String username, String password);
	
	Lecturer findByUsername(String username);
	
	Lecturer findLecturerByFirstName(String firstName);
	
	Lecturer findLecturerById(int id);
	
	@Query ("SELECT l FROM Lecturer l where l.firstName = :lfn and l.lastName = :lln")
	Lecturer findLecturer(@Param("lfn") String lfn, @Param("lln") String lln);
	
}
