package src.optimizer;

import java.util.ArrayList;
import java.util.List;

import com.cs485.gensched.Day;
import com.cs485.gensched.Schedule;
import com.cs485.gensched.ScheduleGenerator;

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
