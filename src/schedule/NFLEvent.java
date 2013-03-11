package src.schedule;


public class NFLEvent {
	private String home;
	private String away;
	private boolean locked;
	

	public NFLEvent(String home, String away) {
		this.home = home;
		this.away = away;
		this.locked = false;
	}

	public String getHome() {
		return home;
	}
	
	public String getAway() {
		return away;
	}
	
	public void lock() {
		locked = true;
	}
	
	public void unlock() {
		locked = false;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public String toString() {
		return String.format("%s at %s", away, home);
	}
	
	public boolean hasTeam(String team) {
		if(home.equals(team) || away.equals(team)) {
			return true;
		} else {
			return false;
		}
	}
}
