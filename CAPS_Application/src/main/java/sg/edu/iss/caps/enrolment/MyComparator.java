package sg.edu.iss.caps.enrolment;

import java.util.Comparator;

public class MyComparator implements Comparator<CourseEnrolment> {

	@Override
	public int compare(CourseEnrolment o1, CourseEnrolment o2) {
		if (o1.getId() > o2.getId())
			return 1;
		else if (o1.getId() < o2.getId())
			return -1;
		else
			return 0;
	}

}
