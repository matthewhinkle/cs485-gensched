package src.optimizer;

import java.util.ArrayList;
import java.util.List;

import src.schedule.Day;
import src.schedule.Schedule;
import src.schedule.ScheduleGenerator;


public class EvolvableScheduleFactory extends EvolvableFactory<Schedule> {

	private ScheduleGenerator generator = new ScheduleGenerator();
	
	@Override
	public Schedule createEvolvable() {
		return this.generator.generate(this.makeDays());
	}
	
	private List<Day> makeDays() {
		final List<Day> days = new ArrayList<Day>();
		for(int i = 0; i < 5; i++) {
			days.add(new Day());
		}
		
		return days;
	}

}
