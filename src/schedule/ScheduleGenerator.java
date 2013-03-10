package src.schedule;

import java.util.ArrayList;
import java.util.List;


public class ScheduleGenerator {
	
	public Schedule generate() {
		List<Week> weeks = new ArrayList<Week>();
		for(int i = 0; i < 5; i++) {
			weeks.add(new Week());
		}
		
		return this.generate(weeks);
	}
	
	public Schedule generate(List<Week> weeks) {
		return new Schedule(weeks);
	}
	
//	public Schedule generateDays(List<Day> days) {
//		Random random = new Random();
//		
//		int numEvents = random.nextInt(5) + 1;
//		for(int i = 0; i < numEvents; i++) {
//			
//			Event event = new BasicEvent();
//			
//			for(Day day : days) {
//				if(random.nextBoolean()) {
//					int durationCoefficient = random.nextInt(4) + 1;
//					int durationInMinutes = durationCoefficient * 60;
//					int maxStart = Day.MINUTES_PER_DAY - durationInMinutes;
//					
//					int startMinute = random.nextInt(maxStart);
//					
//					event.addBlockForDay(day, new Block(day, startMinute, durationInMinutes));
//				}
//			}
//		}
//		
//		return new Schedule(days);
//	}
	
}
