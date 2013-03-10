package src.schedule;

import java.util.List;

import src.optimizer.Heuristic;

public class ScheduleHeuristic implements Heuristic<Schedule> {
	
	public Integer getByeWeekValue(Schedule evolvable) {
		Integer value = 0;
		
		List<Week> weeks = evolvable.getWeeks();
		for(int i = 0; i < weeks.size(); i++) {
			
		}
		
		return value;
	}

	@Override
	public Integer getValue(Schedule evolvable) {
		Integer value = 0;
		for(Week w : evolvable.getWeeks()) {
			// Everything is on Day 0 for now.
			Day day = w.getDay(0);
		}
		return value;
	}
}
