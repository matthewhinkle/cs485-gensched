package src.schedule;

import java.util.ArrayList;
import java.util.List;

import src.optimizer.Heuristic;

public class ScheduleHeuristic implements Heuristic<Schedule> {
	
	public Integer getByeWeekValue(Schedule evolvable) {
		Integer value = 0;
		
		List<Week> weeks = evolvable.getWeeks();
		for(int i = 0; i < weeks.size(); i++) {
			Week w = weeks.get(i);
			
			if(i >= 4 && i <= 11) {
				int byeCount = 0;
				for(int j = 0; j < Week.DAYS_PER_WEEK; j++) {
					Day day = w.getDay(j);
					
					List<NFLEvent> events = day.getEvents();
					for(NFLEvent event : events) {
						if(this.isByeEvent(event)) {
							byeCount += 1;
						}
					}
				}
				
				/* there should be as close to 4 team bye weeks during weeks 4-11 */
				value += Math.abs(4 - byeCount);
			} else {		
				for(int j = 0; j < Week.DAYS_PER_WEEK; j++) {
					Day day = w.getDay(j);
					
					List<NFLEvent> events = day.getEvents();
					for(NFLEvent event : events) {
						if(this.isByeEvent(event)) {
							value += 1;
						}
					}
				}
			}
		}
		
		return value;
	}
	
	private boolean isByeEvent(NFLEvent event) {
		return event.getHome().equalsIgnoreCase("BYE") || event.getAway().equalsIgnoreCase("BYE");
	}

	@Override
	public Integer getValue(Schedule evolvable) {
		Integer value = 0;
		for(Week w : evolvable.getWeeks()) {
			// Everything is on Day 0 for now.
			Day day = w.getDay(0);
			List<String> teams = new ArrayList<String>();
			for(NFLEvent e : day.getEvents()) {
				teams.add(e.getAway());
				teams.add(e.getHome());
			}
			
			for(String team : teams) {
				if(!team.equals("BYE")) {
					value += (1 - occurences(team, teams));
				}
			}
		}
		
		value += 0 - this.getByeWeekValue(evolvable);
		
		return value;
	}
	
	public Integer occurences(String find, List<String> list) {
		Integer value = 0;
		
		for(String v : list) {
			if(v.equals(find)) {
				value += 1;
			}
		}
		
		return value;
	}
}
