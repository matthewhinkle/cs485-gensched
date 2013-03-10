package src.optimizer;

import java.util.ArrayList;
import java.util.List;

import src.schedule.Schedule;
import src.schedule.ScheduleGenerator;
import src.schedule.Week;


public class EvolvableScheduleFactory extends EvolvableFactory<Schedule> {

	private ScheduleGenerator generator = new ScheduleGenerator();
	
	@Override
	public Schedule createEvolvable() {
		return this.generator.generate(this.makeWeeks());
	}
	
	private List<Week> makeWeeks() {
		final List<Week> weeks = new ArrayList<Week>();
		for(int i = 0; i < 5; i++) {
			weeks.add(new Week());
		}
		
		return weeks;
	}

}
