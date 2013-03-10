package src.optimizer;

import src.schedule.Schedule;

public class ScheduleHeuristic implements Heuristic<Schedule> {

	@Override
	public Integer getValue(Schedule evolvable) {
		return evolvable.h;
	}

}
