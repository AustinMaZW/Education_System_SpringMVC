package sg.edu.iss.caps.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.lecturer.LecturerInterface;
import sg.edu.iss.caps.lecturer.LecturerRepository;
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.Status;
import sg.edu.iss.caps.security.SecurityConfig;

@Service
public class ManageLecturerImplementation implements LecturerInterface {

	@Autowired
	LecturerRepository lrepo;
	
	@Autowired
	SecurityConfig secConfig;
	

	public String encodePassword(String password) {
		String encryptedPwd = secConfig.getPasswordEncoder().encode(password);
		return encryptedPwd;
	}
	
	@Override
	public void createLecturer(Lecturer lecturer) {
		// TODO Auto-generated method stub
		lecturer.setPassword(encodePassword(lecturer.getPassword()));
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
		lecturer.setPassword(encodePassword(lecturer.getPassword()));
		lrepo.save(lecturer);
	}

	@Override
	public Lecturer findLecturerById(int id) {
		// TODO Auto-generated method stub
		if(lrepo.existsById(id)) {
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
	public Boolean isNewLecturer(int id) {
		// TODO Auto-generated method stub
		return lrepo.existsById(id);
	}

	@Override
	public void removeLecturerById(int id) {
		// TODO Auto-generated method stub	
		Lecturer lecturer = findLecturerById(id);

		if(lecturer.getStatus().equals(Status.AVAILABLE)) {
			lecturer.setStatus(Status.NOTAVAILABLE);
			lrepo.save(lecturer);
		}
		else {
			lecturer.setStatus(Status.AVAILABLE);
			lrepo.save(lecturer);
		}

		
	}

	@Override
	public void deleteLecturerById(int id) {
		// TODO Auto-generated method stub
		lrepo.deleteById(id);
	}


}
