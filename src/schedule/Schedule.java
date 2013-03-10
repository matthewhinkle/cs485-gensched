package src.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.optimizer.Evolvable;


public class Schedule implements Evolvable<Schedule> {
	private List<Week> weeks;
	
	public Schedule() {
		this.weeks = new ArrayList<Week>();
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
	public List<Schedule> mate(Iterable<Schedule> mates) {
		List<Schedule> schedules = new ArrayList<Schedule>();
		
		//schedules.add(new ScheduleGenerator().generate());
		
		return schedules;
	}

	@Override
	public void mutate() {
		// Move a game from one week to another week randomly
		Random random = new Random();
		
		// Get two random weeks
		Week w1 = weeks.get(random.nextInt(weeks.size()));
		Week w2 = weeks.get(random.nextInt(weeks.size()));
		
		// Get random event from week 1
		Day day = w1.getDay(0);
		NFLEvent e = day.getEvents().get(random.nextInt(day.getEvents().size()));
		day.removeEvent(e);
		
		w2.getDay(0).addEvent(e);
	}
}
