package src.schedule;

import java.util.List;

import src.optimizer.EvolvableFactory;


public class EvolvableScheduleFactory extends EvolvableFactory<Schedule> {

	private ScheduleGenerator generator = new ScheduleGenerator();
	private List<NFLEvent> events;
	
	public EvolvableScheduleFactory(List<NFLEvent> events) {
		this.events = events;
	}

	@Override
	public Schedule createEvolvable() {
		return this.generator.generate(events, 17);
	}
}
