package sg.edu.iss.caps.model;

public enum Status {
    AVAILABLE ("Available"), 
    NOTAVAILABLE ("Not Available");
    
    private final String displayStatus;
    
    private Status (String displayStatus) {
    	this.displayStatus= displayStatus;
    }
    
    public String getDisplayStatus() {
    	return displayStatus;
    }
	
}
