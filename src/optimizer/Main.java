package src.optimizer;

import src.schedule.Schedule;

public class Main {

	public static void main(String[] args) {
		final EvolvableFactory<Schedule> factory = new EvolvableScheduleFactory();
		final Heuristic<Schedule> heuristic = new ScheduleHeuristic();
		final GeneticOptimizerOptions options = new GeneticOptimizerOptions();
		
		final GeneticOptimizer<Schedule> optimizer = new GeneticOptimizer<Schedule>(factory, heuristic, options);
		
		final Schedule optimizedSchedule = optimizer.optimize();
		
		System.out.println(optimizedSchedule.getDays());
	}
	
}
