package src.optimizer;

import com.cs485.gensched.Schedule;

public class ScheduleHeuristic implements Heuristic<Schedule> {

	@Override
	public Integer getValue(Schedule evolvable) {
		return evolvable.h;
	}

}
