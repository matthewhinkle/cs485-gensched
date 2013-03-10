package src.schedule;

import java.util.ArrayList;
import java.util.List;

public class Day {
	
	public static final int MINUTES_PER_DAY = 60 * 24;
	
	private List<Event> schedulables = new ArrayList<Event>();
	
	public boolean add(Event schedulable) {
		for(Event s : this.schedulables) {
			if(s.intersects(schedulable)) {
				return false;
			}
		}
		
		return this.schedulables.add(schedulable);
	}
	
	public boolean remove(Event schedulable) {
		return this.schedulables.remove(schedulable);
	}
	
	public boolean contains(Event schedulable) {
		return this.schedulables.contains(schedulable);
	}
	
	public List<Event> getSchedulables() {
		return this.schedulables;
	}
	
}
