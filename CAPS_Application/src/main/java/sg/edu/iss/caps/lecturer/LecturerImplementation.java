package sg.edu.iss.caps.lecturer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Lecturer;

@Service
public class LecturerImplementation implements LecturerInterface {

	@Autowired
	LecturerRepository lrepo;
	
	@Override
	public void createLecturer(Lecturer lecturer) {
		// TODO Auto-generated method stub
		lrepo.save(lecturer);
	}

	@Override
	public Lecturer findLecturerByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return lrepo.findLecturerByFirstName(firstName);

	}

	@Override
	public List<Lecturer> findAllLecturer() {
		// TODO Auto-generated method stub
		return lrepo.findAll();
	}

	@Override
	public void updateLecturer(Lecturer lecturer) {
		// TODO Auto-generated method stub
		lrepo.save(lecturer);
	}

	@Override
	public void deleteLecturer(Lecturer lecturer) {
		// TODO Auto-generated method stub
		lrepo.delete(lecturer);
	}


}
