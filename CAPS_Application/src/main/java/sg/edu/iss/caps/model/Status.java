package sg.edu.iss.caps.model;

public enum Status {
    AVAILABLE ("Available","Open"),
    NOTAVAILABLE ("Not Available","Closed"),
    FULL (null,"Full"),
    ONGOING (null,"Ongoing"),
    COMPLETE (null,"Complete");
    
    private final String displayStatus;
    private final String displayEnrolStatus;
    
    Status (String displayStatus, String displayEnrolStatus) {
    	this.displayStatus= displayStatus;
    	this.displayEnrolStatus = displayEnrolStatus;
    }
    
    public String getDisplayStatus() {
    	return displayStatus;
    }
    public String getDisplayEnrolStatus() {
        return displayEnrolStatus;
    }
    
    public boolean isNull() {
    	return this.displayStatus==null;
    }
}
