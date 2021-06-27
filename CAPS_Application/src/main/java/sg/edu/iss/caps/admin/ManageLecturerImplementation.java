package sg.edu.iss.caps.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.lecturer.LecturerInterface;
import sg.edu.iss.caps.lecturer.LecturerRepository;
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.Status;

@Service
public class ManageLecturerImplementation implements LecturerInterface {

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
		List<Lecturer> lecList = lrepo.findAll();
		return lecList;
	}

	@Override
	public void updateLecturer(Lecturer lecturer) {
		// TODO Auto-generated method stub		
		lrepo.save(lecturer);
	}

	@Override
	public Lecturer findLecturerById(int id) {
		// TODO Auto-generated method stub
		if(lrepo.findById(id)!=null) {
			Lecturer lecturer = lrepo.findById(id).get();
			return lecturer;
		}
		else
			return null;
	}

	@Override
	public List<Lecturer> findAllAvailableLecturer() {
		// TODO Auto-generated method stub
		List<Lecturer> lecList= lrepo.findAll().stream()
				.filter(e-> e.getStatus().equals(Status.AVAILABLE))
				.collect(Collectors.toList());
		return lecList;
	}

	@Override
	public Boolean isNew(int id) {
		// TODO Auto-generated method stub
		
		if(lrepo.findById(id)!=null)
			return false;
		else
			return true;
	}

	@Override
	public void removeLecturerById(int id) {
		// TODO Auto-generated method stub
		Lecturer lecturer = findLecturerById(id);
		lecturer.setStatus(Status.NOTAVAILABLE);
		lrepo.save(lecturer);
		
	}


}
