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
	public Double getValue(Schedule schedule) {
		Double value = 0.0;
		value += getOccurencesValue(schedule);
		value += 0 - this.getByeWeekValue(schedule);
		value += getSixteenGamesPerWeek(schedule);
		return value;
	}
	
	public Double getSixteenGamesPerWeek(Schedule schedule) {
		Double value = 0.0;
		
		for(Week w : schedule.getWeeks()) {
			Double weekValue = 16.0;
			List<NFLEvent> events = w.getDay(0).getEvents();
			double byes = 0;
			double games = 0;
			for(NFLEvent e : events) {
				if(e.getAway().equals("BYE") || e.getHome().equals("BYE")) {
					byes += 1;
				} else {
					games += 1;
				}
			}
			byes /= 2.0;
			value -= weekValue - Math.abs(byes + games);
		}
		
		return value;
	}

	public Integer getOccurencesValue(Schedule evolvable) {
		Integer value = 0;
		for(Week w : evolvable.getWeeks()) {
			Integer weekValue = 0;

			// Everything is on Day 0 for now.
			Day day = w.getDay(0);
			List<String> teams = new ArrayList<String>();
			for(NFLEvent e : day.getEvents()) {
				teams.add(e.getAway());
				teams.add(e.getHome());
			}

			for(int i = 0; i < teams.size(); i++) {
				String team = teams.get(i);
				if(!team.equals("BYE") && !teams.subList(0, i).contains(team)) {
					//System.out.println("Team: " + team + " occurs " + occurences(team, teams) + " times.");
					weekValue += (1 - occurences(team, teams));
				}
			}
			//System.out.println("Value for week: " + weekValue);
			value += weekValue;
		}		
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
