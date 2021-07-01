package sg.edu.iss.caps.lecturer;

import java.util.List;

import sg.edu.iss.caps.course.Course;

public interface LecturerInterface {
	public void createLecturer(Lecturer lecturer);

//	public Lecturer findLecturerByFirstName(String name);

	public List<Lecturer> findAllLecturer(); //ManageLecturerController

//	public List<Lecturer> findAllAvailableLecturer();

	public void updateLecturer(Lecturer lecturer); // ManageLecturerController

//	public Lecturer findLecturerById(int id);

	public boolean isNewLecturer(int id);

//	public void removeLecturerById(int id);

	public void deleteLecturerById(int id); // ManageLecturerController

	public Lecturer findLecturerByUsername(String username); //ViewCourseTaughtController
	
	public List<Lecturer> findLecturersByCourses(Course course);
}
