package src.schedule;

import java.util.ArrayList;
import java.util.List;

import src.optimizer.Heuristic;

public class ScheduleHeuristic implements Heuristic<Schedule> {

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
				value += (1 - occurences(team, teams));
			}
		}
		System.out.println(value);
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
