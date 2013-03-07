package src.optimizer;

import java.util.List;

public interface Evolvable<T extends Evolvable<T>> {
	/*
	 * Get the number of mates required to evolve
	 */
	public int getNumberOfMates();
	
	/*
	 * Get the children produced from mating
	 */
	public List<T> mate(Iterable<T> mates);
	
	/*
	 * Mutate this evolvable
	 */
	public void mutate();
}
