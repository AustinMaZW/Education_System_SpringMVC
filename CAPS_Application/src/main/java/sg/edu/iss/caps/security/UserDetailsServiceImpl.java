package sg.edu.iss.caps.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.admin.Admin;
import sg.edu.iss.caps.admin.AdminRepository;
import sg.edu.iss.caps.lecturer.LecturerRepository;
import sg.edu.iss.caps.model.Lecturer;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.student.StudentRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	AdminRepository arepo;
	@Autowired
	StudentRepository srepo;
	@Autowired
	LecturerRepository lrepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin adminFromDB = arepo.findByUsername(username);
		Student studentFromDB = srepo.findByUsername(username);
		Lecturer lecturerFromDB = lrepo.findByUsername(username);
		
		if (adminFromDB!=null){
			return new UserDetailsImpl(adminFromDB, "ROLE_ADMIN");
		}
		else if (studentFromDB!=null){
			return new UserDetailsImpl(studentFromDB, "ROLE_STUDENT");
		}
		else if (lecturerFromDB!=null){
			return new UserDetailsImpl(lecturerFromDB, "ROLE_LECTURER");
		}
		else {
			throw new UsernameNotFoundException("Not found: " +username);
		}
	}
	
	
}
