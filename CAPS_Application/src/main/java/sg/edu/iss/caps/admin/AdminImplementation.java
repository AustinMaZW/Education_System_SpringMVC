package sg.edu.iss.caps.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.security.SecurityConfig;
import sg.edu.iss.caps.student.StudentRepository;

@Service
public class AdminImplementation implements AdminInterface {
	@Autowired
	AdminRepository arepo;
	
	@Autowired
	StudentRepository srepo;
	
	@Autowired
	SecurityConfig secConfig;

	public String encodePassword(String password) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	public void createAdmin(Admin admin) {
		arepo.save(admin);
	}
	
	public void updateAdmin(Admin admin) {
		arepo.save(admin);
	}
	
	public void deleteAdmin(Admin admin) {
		arepo.delete(admin);
	}

	public List<Student> listAllStudents() {
		return srepo.findAll();
	}

	public Student saveStudent(Student student) {
		if (student.getId() == 0) { // add new
			student.setPassword(encodePassword(student.getPassword()));
		} 
		else { // edit
			if (student.getPassword() == null) {
				Student db_std = srepo.findById(student.getId()).get();
				student.setPassword(db_std.getPassword());
			}
		}
		return srepo.save(student);
	}

	public Student getStudentById(Integer id) {
		return srepo.findById(id).get();
	}
	
	public void deleteStudent(Student student) {
		srepo.delete(student);
	}
	//old code
//	public boolean authenticate(Admin admin) {
//		Admin fromDB = arepo.findAdminByUsernameAndPassword(admin.getUsername(), admin.getPassword());
//		if (fromDB!=null){
//			return true;
//		}
//		return false;
//	}
}
