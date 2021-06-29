package sg.edu.iss.caps.model;

public enum Status {
    AVAILABLE ("Open"),
    NOTAVAILABLE ("Closed"),
    FULL ("Full");
    
    private final String displayStatus;
    
    private Status (String displayStatus) {
    	this.displayStatus= displayStatus;
    }
    
    public String getDisplayStatus() {
    	return displayStatus;
    }
	
}
