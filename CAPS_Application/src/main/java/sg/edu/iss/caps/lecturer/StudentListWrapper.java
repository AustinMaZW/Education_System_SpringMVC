package sg.edu.iss.caps.lecturer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sg.edu.iss.caps.student.Student;

public class StudentListWrapper {

	private List<Student> students = new ArrayList<>();
	private Map<Integer, Double> grades = new HashMap<>();
	
	public StudentListWrapper() {}
	
	public StudentListWrapper(List<Student> studentList) {
		this.students = studentList;
	}
	
	public StudentListWrapper(List<Student> studentList, Map<Integer, Double> grades) {
		this.students = studentList;
		this.grades = grades;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> studentList) {
		this.students = studentList;
	}
	
	public void addGrade(int studentId, double grade) {
		grades.put(studentId, grade);
	}
	
	public Map<Integer, Double> getGrades(){
		return grades;
	}
}