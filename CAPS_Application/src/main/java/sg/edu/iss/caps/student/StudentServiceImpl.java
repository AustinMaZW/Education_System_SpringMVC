package sg.edu.iss.caps.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.enrolment.CourseEnrolment;
import sg.edu.iss.caps.enrolment.EnrolRepository;
import sg.edu.iss.caps.enrolment.EnrolmentService;
import sg.edu.iss.caps.model.Grade;
import sg.edu.iss.caps.model.Status;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	StudentRepository srepo;
	@Autowired
	EnrolRepository erepo;
	@Autowired
	EnrolmentService eservice;

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

	@Override
	public boolean setEnrol(CourseEnrolment enrol, Student stu) {
		Map<CourseEnrolment, Double> newEnrol = stu.getGrades();
		if (eservice.findStudentsByEnrol(enrol).size() < enrol.getCapacity()) {
			newEnrol.put(enrol, -1.0);
			stu.setGrades(newEnrol);
			srepo.save(stu);
			if (eservice.findStudentsByEnrol(enrol).size() >= enrol.getCapacity()) {
				enrol.setStatus(Status.FULL);
				erepo.save(enrol);
				return true;
			}
			return true;
		}
		return false;
	}

	@Override
	public void cancel(Student stu, CourseEnrolment enrol) {
		Map<CourseEnrolment, Double> gra = stu.getGrades();
		gra.remove(enrol);
		stu.setGrades(gra);
		srepo.save(stu);
		if (eservice.findStudentsByEnrol(enrol).size() < enrol.getCapacity()) {
			enrol.setStatus(Status.AVAILABLE);
			erepo.save(enrol);
		}
	}

	@Override
	public Double getCAP(Student s) {
		Map<CourseEnrolment, Double> gradeslist = s.getGrades();
		Double grades = 0.0;
		Double unitsTakenTotal = 0.0;

		ArrayList<Double> modularCreditList = new ArrayList<Double>();
		ArrayList<Double> valueList = new ArrayList<Double>();

		// get course weight-age via modular credit
		for (CourseEnrolment courseEId : gradeslist.keySet()) {
			Double modularcredit = Double.parseDouble(courseEId.getCourse().getModularcredits());
			modularCreditList.add(modularcredit);
		}

		// get grade point obtained for each course using marks (out of 100)
		for (Double grade : gradeslist.values()) {
			Grade gradeEnum = Grade.valueofGradePoint(grade);
			Double gradepoint = Double.parseDouble(Grade.valueofGrade(gradeEnum));
			valueList.add(gradepoint);
		}

		// weighted calculation
		if (modularCreditList.size() == valueList.size()) {
			for (int i = 0; i < modularCreditList.size(); i++) {
				Double grade = modularCreditList.get(i) * valueList.get(i);
				grades += grade;
				unitsTakenTotal += modularCreditList.get(i);
			}
		}
		grades = grades / unitsTakenTotal;
		return Math.round(grades * 100.0) / 100.0;
	}

	@Override
	public Map<CourseEnrolment, String> getGradesAlphabet(Student s) {
		Map<CourseEnrolment, Double> gradeslist = s.getGrades();
		Map<CourseEnrolment, String> gradesAlphabetlist = new HashMap<CourseEnrolment, String>();
		ArrayList<CourseEnrolment> courseEId = new ArrayList<CourseEnrolment>();
		ArrayList<String> gradesAlphabet = new ArrayList<String>();

		for (CourseEnrolment course : gradeslist.keySet()) {
			courseEId.add(course);
		}
		for (Double grade : gradeslist.values()) {
			Grade gradeEnum = Grade.valueofGradePoint(grade);
			gradesAlphabet.add(gradeEnum.grade);
		}

		for (int i = 0; i < courseEId.size(); i++) {
			gradesAlphabetlist.put(courseEId.get(i), gradesAlphabet.get(i));
		}
		return gradesAlphabetlist;
	}

	public Double getMC(Student s) {
		Map<CourseEnrolment, Double> gradeslist = s.getGrades();
		Double nograde = -1.0;
		gradeslist.entrySet().removeIf(entry -> (nograde.equals(entry.getValue())));
		Double unitsTakenTotal = 0.0;

		for (CourseEnrolment courseEId : gradeslist.keySet()) {
			Double modularcredit = Double.parseDouble(courseEId.getCourse().getModularcredits());
			unitsTakenTotal += modularcredit;
		}

		return unitsTakenTotal;
	}

	@Override
	@Transactional
	public Student updateGradeByStudentId(int studentId, int enrolId, double grade) {
		CourseEnrolment courseEnrolment = erepo.findCourseEnrolmentById(enrolId);
		Student s = srepo.findStudentById(studentId);
		if (s.getGrades().get(courseEnrolment) == null) {
			return null;
		}

		s.getGrades().put(courseEnrolment, grade);
		Double cap = getCAP(s);
		s.setGpa(cap);
		return srepo.save(s);

	}
}
