package sg.edu.iss.caps.lecturer;

import java.util.List;

import sg.edu.iss.caps.model.Lecturer;

public interface LecturerInterface {
	public void createLecturer(Lecturer lecturer);
	public Lecturer findLecturerByName(String name);
	public List<Lecturer> findAllLecturer();
	public void updateLecturer(Lecturer lecturer);
	public void deleteLecturer(Lecturer lecturer);
	
	
}
