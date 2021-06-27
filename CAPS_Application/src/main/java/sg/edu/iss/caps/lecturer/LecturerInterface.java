package sg.edu.iss.caps.lecturer;

import java.util.List;

import sg.edu.iss.caps.model.Lecturer;

public interface LecturerInterface {
	public void createLecturer(Lecturer lecturer);
	public Lecturer findLecturerByFirstName(String name);
	public List<Lecturer> findAllLecturer();
	public List<Lecturer> findAllAvailableLecturer();
	public void updateLecturer(Lecturer lecturer);
	public Lecturer findLecturerById(int id);
	public Boolean isNew(int id);
	public void removeLecturerById(int id);	
}
