package src.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ScheduleGenerator {
	
	public Schedule generate(List<NFLEvent> events, int numWeeks) {
		List<Week> weeks = new ArrayList<Week>();
		for(int i = 0; i < numWeeks; i++) {
			weeks.add(new Week());
		}
		
		Schedule schedule = new Schedule(weeks);
		Random random = new Random();
		for(NFLEvent event : events) {
			int week = random.nextInt(numWeeks);
			schedule.scheduleEvent(event, week);
		}
		
		return schedule;
	}
}
