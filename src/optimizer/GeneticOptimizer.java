package src.optimizer;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class GeneticOptimizer<T extends Evolvable<T>> {
	
	private EvolvableFactory<T> factory;
	private Heuristic<T> heuristic;
	private GeneticOptimizerOptions options;
	private Random random;

	public GeneticOptimizer(EvolvableFactory<T> factory, Heuristic<T> heuristic, GeneticOptimizerOptions options) {
		this.factory = factory;
		this.heuristic = heuristic;
		this.options = options;
		random = new Random();
	}
	
	public T optimize() {
		/*
		 * Create the initial population
		 */
		List<T> population = new ArrayList<T>();
		for(int i = 0; i < options.getPopulationSize(); i++) {
			population.add(factory.createEvolvable());
		}

		/*
		 * For every iteration:
		 * 	1) Evolve
		 * 	2) Mutate
		 * 	3) Sort list according to heuristic
		 * 	4) Trim population down to size
		 */
		for(int i = 0; i < options.getIterations(); i++) {
			evolve(population);
			mutate(population);
			try {
				sort(population);
			} catch (Exception e) {
				System.err.println(e.getMessage());
				for(T t : population) {
					System.err.println("--- Schedule ---");
					System.err.println(t.toString());
				}
				System.exit(1);
			}
			population = trim(population);
			System.out.println("--- Schedules ---");
			for(T t : population) {
				System.out.println(t.toString());
			}
			System.out.println("--- Schedules ---\n\n");
		}

		return (T) population.get(0);
	}
	
	/*
	 * Sort the population using the heuristic
	 */
	public void sort(List<T> population) {
		Collections.sort(population, new Comparator<T>() {
			@Override
			public int compare(T arg0, T arg1) {
				return -1 * heuristic.getValue(arg0).compareTo(heuristic.getValue(arg1));
			}
		});
	}
	
	/*
	 * Trim the population down to the population size
	 */
	public List<T> trim(List<T> population) {
		return population.subList(0, options.getPopulationSize());
	}

	/*
	 * Evolve the population
	 */
	public void evolve(List<T> population) {
		// The children produced from mating
		List<T> children = new ArrayList<T>();

		for(Evolvable<T> e : population) {
			Set<T> parents = new HashSet<T>();

			/*
			 * Get the mates required for evolving
			 */
			while(parents.size() < e.getNumberOfMates()) {
				parents.add((T) population.get(random.nextInt(population.size())));
			}
			children.addAll(e.mate(parents));
		}
		
		population.addAll(children);
	}

	/*
	 * Mutate the population
	 */
	public void mutate(List<T> population) {
		for(T e : population) {
			if(random.nextDouble() <= options.getChanceToMutate()) {
				e.mutate();
			}
		}
	}
}