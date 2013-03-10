package src.schedule;

import src.optimizer.Heuristic;

public class ScheduleHeuristic implements Heuristic<Schedule> {

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
