package sg.edu.iss.caps.lecturer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {

	Lecturer findLecturerByUsernameAndPassword(String username, String password);

	Lecturer findByUsername(String username);

	Lecturer findLecturerByFirstName(String firstName);

//	@Query ("SELECT l FROM Lecturer l where l.firstName = :lfn and l.lastName = :lln")
//	Lecturer findLecturer(@Param("lfn") String lfn, @Param("lln") String lln);

}
