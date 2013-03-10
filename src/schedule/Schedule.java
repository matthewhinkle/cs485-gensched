package src.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import src.optimizer.Evolvable;


public class Schedule implements Evolvable<Schedule> {
	public int h = (new Random()).nextInt();
	private List<Day> days;
	
	public Schedule() {
		this.days = new ArrayList<Day>();
	}
	
	public Schedule(List<Day> days) {
		this.days = days;
	}
	
	public List<Day> getDays() {
		return this.days;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
//		for(Day day : this.days) {
//			sb.append(day.getSchedulables()).append("\n");
//		}
		
		sb.append(h);
		
		return sb.toString();
	}

	@Override
	public int getNumberOfMates() {
		return 1;
	}

	@Override
	public List<Schedule> mate(Iterable<Schedule> mates) {
		List<Schedule> schedules = new ArrayList<Schedule>();
		
		schedules.add(new ScheduleGenerator().generate());
		
		return schedules;
	}

	@Override
	public void mutate() {
		// TODO Auto-generated method stub	
	}
}
