package com.cs485.gensched;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ScheduleGenerator {
	
	public Schedule generate() {
		List<Day> days = new ArrayList<Day>();
		for(int i = 0; i < 5; i++) {
			days.add(new Day());
		}
		
		return this.generate(days);
	}
	
	public Schedule generate(List<Day> days) {
		Random random = new Random();
		
		int numEvents = random.nextInt(5) + 1;
		for(int i = 0; i < numEvents; i++) {
			
			Event event = new BasicEvent();
			
			for(Day day : days) {
				if(random.nextBoolean()) {
					int durationCoefficient = random.nextInt(4) + 1;
					int durationInMinutes = durationCoefficient * 60;
					int maxStart = Day.MINUTES_PER_DAY - durationInMinutes;
					
					int startMinute = random.nextInt(maxStart);
					
					event.addBlockForDay(day, new Block(day, startMinute, durationInMinutes));
				}
			}
		}
		
		return new Schedule(days);
	}
	
}
