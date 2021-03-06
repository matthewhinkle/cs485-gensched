package src.schedule;

import java.util.ArrayList;
import java.util.List;

public class Day {
	
	public static final int MINUTES_PER_DAY = 60 * 24;
	
	private List<NFLEvent> events;
	
	public Day(Day day) {
		events = new ArrayList<NFLEvent>();
		for(NFLEvent e : day.getEvents()) {
			this.addEvent(e);
		}
	}

	public Day() {
		events = new ArrayList<NFLEvent>();
	}

	public boolean addEvent(NFLEvent e) {
		return this.events.add(e);
	}
	
	public boolean removeEvent(NFLEvent block) {
		return this.events.remove(block);
	}
	
	public List<NFLEvent> getEvents() {
		return this.events;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(NFLEvent e : events) {
			sb.append(e.toString() + "\n");
		}
		return sb.toString();
	}

	public double eventSize() {
		double value = 0;
		for(NFLEvent e : events) {
			if(e.getAway().equals("BYE") || e.getHome().equals("BYE")) {
				value += 0.5;
			} else {
				value += 1.0;
			}
		}
		return value;
	}
}
