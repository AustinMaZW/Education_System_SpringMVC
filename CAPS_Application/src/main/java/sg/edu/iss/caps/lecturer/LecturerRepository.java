package sg.edu.iss.caps.lecturer;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.iss.caps.model.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer>{
	public Lecturer findLecturerByUsernameAndPassword(String username, String password);
	public Lecturer findByUsername(String username);
	public Lecturer findLecturerByName(String name);
	public Lecturer findLecturerById(int id);
	
}
