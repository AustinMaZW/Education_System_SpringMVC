package sg.edu.iss.caps.lecturer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Lecturer;

@Service
public class LecturerImplementation implements LecturerInterface {

	@Autowired
	LecturerRepository lrepo;
	
	@Override
//	@Transactional
	public void createLecturer(Lecturer lecturer) {
		// TODO Auto-generated method stub
		String lfn = lecturer.getFirstName();
		String lln = lecturer.getLastName();
		if (lrepo.findLecturer(lfn, lln) != null)
			return;
		else
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
