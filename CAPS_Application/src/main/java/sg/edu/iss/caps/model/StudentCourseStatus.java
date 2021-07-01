package sg.edu.iss.caps.model;

public enum StudentCourseStatus {
    PASS ("Pass"),
    FAIL ("Fail"),
    ONGOING ("Ongoing");
    
    private final String displayStatus;
    
    StudentCourseStatus(String displayStatus) {
    	this.displayStatus= displayStatus;
    }
    
    public String getDisplayStatus() {
    	return displayStatus;
    }

}
