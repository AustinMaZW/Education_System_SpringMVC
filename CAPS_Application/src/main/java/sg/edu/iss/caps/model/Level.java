package sg.edu.iss.caps.model;

public enum Level {
    SENIOR("Senior"), 
    JUNIOR("Junior");
	
	private final String displayLevel;
	
    private Level (String displayLevel) {
    	this.displayLevel= displayLevel;
    }
    
    public String getDisplayLevel() {
    	return displayLevel;
    }
	
}
