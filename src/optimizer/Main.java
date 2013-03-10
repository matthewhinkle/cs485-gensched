package src.optimizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import src.schedule.EvolvableScheduleFactory;
import src.schedule.NFLEvent;
import src.schedule.Schedule;
import src.schedule.ScheduleHeuristic;

public class Main {

	public static void main(String[] args) throws IOException {
		List<NFLEvent> events = getNFLEvents();

		final EvolvableFactory<Schedule> factory = new EvolvableScheduleFactory(events);
		final Heuristic<Schedule> heuristic = new ScheduleHeuristic();
		final GeneticOptimizerOptions options = new GeneticOptimizerOptions();
		options.setMaxHeuristic(0);
		options.setIterations(1000);
		
		final GeneticOptimizer<Schedule> optimizer = new GeneticOptimizer<Schedule>(factory, heuristic, options);
		
		final Schedule optimizedSchedule = optimizer.optimize();
		System.out.println(heuristic.getValue(optimizedSchedule));
		System.out.println(optimizedSchedule.toString());
	}

	public static List<NFLEvent> getNFLEvents() throws IOException {
		List<NFLEvent> games = new ArrayList<NFLEvent>();
		
		BufferedReader br = new BufferedReader(new FileReader(new File("games.txt")));
		String line;
		int count = 0;
		String currentTeam = null;

		while((line = br.readLine()) != null) {
			if(count == 0) {
				// On a team line
				currentTeam = line;
				games.add(new NFLEvent(currentTeam, "BYE"));
			} else if (count == 1) {
				// On home (count == 1) or away line (count == 2)
				String[] opponents = line.split(",");
				for(String s : opponents) {
					games.add(new NFLEvent(currentTeam, s));
				}
			}

			count++;
			count = count > 2 ? 0 : count;
		}

		br.close();
		return games;
	}
	
}
