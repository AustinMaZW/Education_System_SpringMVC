package sg.edu.iss.caps.student;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.caps.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepository srepo;

	@Override
	public ArrayList<Student> findAllStudent() {
		// TODO Auto-generated method stub
		return (ArrayList<Student>) srepo.findAll();
	}

	@Override
	public Student findStudentById(int id) {
		// TODO Auto-generated method stub
		return srepo.findById(id).get();
	}

	@Override
	public Student findStudentByFirstName(String name) {
		Student stu = srepo.findStudentByFirstName(name);
		return stu;
	}

	@Override
	public Student findStudentByUsername(String username) {
		Student stu = srepo.findByUsername(username);
		return stu;
	}

}
