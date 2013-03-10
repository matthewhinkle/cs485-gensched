package src.schedule;

import java.util.ArrayList;
import java.util.List;

import src.optimizer.Heuristic;

public class ScheduleHeuristic implements Heuristic<Schedule> {
	
	public Integer getByeWeekValue(Schedule evolvable) {
		Integer value = 0;
		
		List<Week> weeks = evolvable.getWeeks();
		for(int i = 0; i < weeks.size(); i++) {
			
		}
		
		return value;
	}

	@Override
	public Integer getValue(Schedule evolvable) {
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
