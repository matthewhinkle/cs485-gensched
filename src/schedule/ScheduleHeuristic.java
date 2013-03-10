package src.schedule;

import java.util.List;

import src.optimizer.Heuristic;

public class ScheduleHeuristic implements Heuristic<Schedule> {
	
	public Integer getByeWeekValue(Schedule evolvable) {
		Integer value = 0;
		
		List<Week> weeks = evolvable.getWeeks();
		for(int i = 0; i < weeks.size(); i++) {
			if(i >= 4 && i <= 11) {
				continue;
			}
			
			Week w = weeks.get(i);
			for(int j = 0; j < Week.DAYS_PER_WEEK; j++) {
				Day day = w.getDay(j);
				
				List<NFLEvent> events = day.getEvents();
				for(NFLEvent event : events) {
					if(event.getHome().equalsIgnoreCase("bye")
					|| event.getAway().equalsIgnoreCase("bye")) {
						value -= 1;
					}
				}
			}
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
