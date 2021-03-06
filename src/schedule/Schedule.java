package src.schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import src.optimizer.Evolvable;


public class Schedule implements Evolvable<Schedule> {
	private List<Week> weeks;
	
	public Schedule() {
		this.weeks = new ArrayList<Week>();
	}
	
	public Schedule(int numWeeks) {
		this.weeks = new ArrayList<Week>();
		for(int i = 0; i < numWeeks; i++) {
			weeks.add(new Week());
		}
	}
	
	public Schedule(List<Week> weeks) {
		this.weeks = weeks;
	}
	
	public List<Week> getWeeks() {
		return this.weeks;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Week w : weeks) {
			sb.append(w.getDay(0).eventSize() + "\n");
			sb.append(w.toString() + "\n");
		}
		return sb.toString();
	}
	
	public void scheduleEvent(NFLEvent e, int week) {
		weeks.get(week).getDay(0).addEvent(e);
	}

	@Override
	public int getNumberOfMates() {
		return 1;
	}

	@Override
	public List<Schedule> mate(List<Schedule> mates) {
		List<Schedule> schedules = new ArrayList<Schedule>();
		
		Map<NFLEvent, List<Integer>> eventWeeks = new HashMap<NFLEvent, List<Integer>>();
		
		for(int i = 0; i < this.getWeeks().size(); i++) {
			Day d = this.weeks.get(i).getDay(0);
			for(NFLEvent e : d.getEvents()) {
				if(eventWeeks.get(e) == null) {
					eventWeeks.put(e, new ArrayList<Integer>());
				}
				eventWeeks.get(e).add(i);
			}
		}
		
		for(Schedule s : mates) {
			for(int i = 0; i < s.getWeeks().size(); i++) {
				Day d = s.weeks.get(i).getDay(0);
				for(NFLEvent e : d.getEvents()) {
					if(eventWeeks.get(e) == null) {
						eventWeeks.put(e, new ArrayList<Integer>());
					}
					eventWeeks.get(e).add(i);
				}
			}
		}
		
		Random random = new Random();
		for(int j = 0; j < 3; j++) {
			Schedule child = new Schedule(this.weeks.size());
			for(Entry<NFLEvent, List<Integer>> entry : eventWeeks.entrySet()) {
				int r = random.nextInt(entry.getValue().size());
				child.getWeeks().get(entry.getValue().get(r)).getDay(0).addEvent(entry.getKey());
			}
			schedules.add(child);
		}
		return schedules;
	}

	@Override
	public void mutate() {
		// Move a game from one week to another week randomly
		Random random = new Random();
		double r = random.nextDouble(); 
		if(r < 0.75) {
			// Get two random weeks
			Week w1 = weeks.get(random.nextInt(weeks.size()));
			Week w2 = weeks.get(random.nextInt(weeks.size()));
			
			// Get random event from week 1
			Day day = w1.getDay(0);
			if(day.getEvents().size() != 0) {
				NFLEvent e = day.getEvents().get(random.nextInt(day.getEvents().size()));
				day.removeEvent(e);
				w2.getDay(0).addEvent(e);
			}
		} else {
			Week smallest = null;
			Week largest  = null;
			
			for(Week w : weeks) {
				if(smallest == null || w.getDay(0).eventSize() < smallest.getDay(0).eventSize()) {
					smallest = w;
				}
				
				if(largest == null || w.getDay(0).eventSize() > largest.getDay(0).eventSize()) {
					largest = w;
				}
			}
			
			if(!(smallest.getDay(0).eventSize() == largest.getDay(0).eventSize())) {
				Day day = largest.getDay(0);
				NFLEvent e = null;
				while((e = day.getEvents().get(random.nextInt(day.getEvents().size()))).getAway().equals("BYE")) {}
				day.removeEvent(e);
				smallest.getDay(0).addEvent(e);
			}
		}
	}
}
