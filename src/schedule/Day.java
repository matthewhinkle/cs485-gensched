package src.schedule;

import java.util.ArrayList;
import java.util.List;

public class Day {
	
	public static final int MINUTES_PER_DAY = 60 * 24;
	
	private List<NFLEvent> events = new ArrayList<NFLEvent>();
	
	public boolean addEvent(NFLEvent e) {
		return this.events.add(e);
	}
	
	public boolean removeEvent(NFLEvent block) {
		return this.events.remove(block);
	}
	
	public List<NFLEvent> getEvents() {
		return events;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(NFLEvent e : events) {
			sb.append(e.toString() + "\n");
		}
		return sb.toString();
	}
}
