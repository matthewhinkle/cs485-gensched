package src.schedule;

public class NFLEvent {
	private String home;
	private String away;

	public NFLEvent(String home, String away) {
		this.home = home;
		this.away = away;
	}
	
	public String getHome() {
		return home;
	}
	
	public String getAway() {
		return away;
	}
	
	public String toString() {
		return String.format("%s at %s", away, home);
	}
}
