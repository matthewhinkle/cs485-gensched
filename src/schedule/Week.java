package src.schedule;

import java.util.ArrayList;
import java.util.List;

public class Week {
	
	public static final int DAYS_PER_WEEK = 7;
	
	private final List<Day> days = new ArrayList<Day>();
	
	public Week() {
		for(int i = 0; i < Week.DAYS_PER_WEEK; i++) {
			this.days.add(new Day());
		}
	}
	
	public Day getDay(int day) {
		return days.get(day);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("--- Week ---\n");
		for(Day d : days) {
			sb.append(d.toString());
		}
		return sb.toString();
	}
}
