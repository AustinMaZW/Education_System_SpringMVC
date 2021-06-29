package sg.edu.iss.caps.model;

import java.util.HashMap;
import java.util.Map;

public enum Grade {
	A1 ("A+","5.0","Excellent"), 
	A2 ("A","5.0","Excellent"), 
	A3 ("A-","4.5","Excellent"), 
	B1 ("B+","4.0","Very Good"), 
	B2 ("B","3.5","Very Good"), 
	B3 ("B-","3.0","Very Good"), 
	C1 ("C+","2.5","Good"), 
	C2 ("C","2.0","Satisfactor"), 
	D1 ("D+","1.5","Probationary Grade"), 
	D2 ("D","1.0","Probationary Grade"), 
	F ("F","0.0","FAIL"),
	Empty(" ", " ","Pending Grade"),
	Error(" ", " ","Error");

	private static final Map<String, Grade> BY_GRADE = new HashMap<>();
	private static final Map<String, Grade> BY_GRADE_POINT = new HashMap<>();
	private static final Map<String, Grade> BY_DESCRIPTION = new HashMap<>();

	
	static {
		for(Grade g:values()) {
			BY_GRADE.put(g.grade, g);
			BY_GRADE_POINT.put(g.gradepoint, g);
			BY_DESCRIPTION.put(g.description, g);
		}
	}
	
	public final String grade;
	public final String gradepoint;
	public final String description;

	
	Grade(String grade, String gradepoint, String description) {
		this.grade = grade;
		this.gradepoint = gradepoint;
		this.description = description;
	}
	
	public static Grade valueofGradePoint(Double mark) {
		if(mark>=85 && mark <= 100)
			return A1;
		else if(mark>=80 && mark <= 84)
			return A2;
		else if(mark>=75 && mark <= 79)
			return A3;
		else if(mark>=70 && mark <= 74)
			return B1;
		else if(mark>=65 && mark <= 69)
			return B2;
		else if(mark>=60 && mark <= 64)
			return B3;
		else if(mark>=55 && mark <= 59)
			return C1;
		else if(mark>=50 && mark <= 54)
			return C2;
		else if(mark>=45 && mark <= 49)
			return D1;
		else if(mark>=40 && mark <= 44)
			return D2;
		else if(mark>=0 && mark <= 39)
			return F;
		else if(mark.isNaN())
			return Empty;
		else 
			return Error;
	}	
	
	public static String valueofGrade(Grade grade) {
		return grade.gradepoint;
	}
}

