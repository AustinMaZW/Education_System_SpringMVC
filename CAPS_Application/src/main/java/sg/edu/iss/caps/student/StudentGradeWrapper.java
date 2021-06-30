package sg.edu.iss.caps.student;

import java.util.HashMap;
import java.util.Map;

public class StudentGradeWrapper {

	private Map<Integer, Double> grades = new HashMap<>();
	
	public StudentGradeWrapper() {}
	
	public void addGrade(int studentId, double grade) {
		grades.put(studentId, grade);
	}
	
	public Map<Integer, Double> getGrades(){
		return grades;
	}
}
