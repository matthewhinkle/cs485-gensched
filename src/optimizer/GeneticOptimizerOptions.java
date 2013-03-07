package src.optimizer;

public class GeneticOptimizerOptions {
	private double chanceToMutate = 0.05;
	private int populationSize = 20;
	private int iterations = 20;

	/**
	 * @return the chanceToMutate
	 */
	public double getChanceToMutate() {
		return chanceToMutate;
	}
	/**
	 * @param chanceToMutate the chanceToMutate to set
	 */
	public void setChanceToMutate(double chanceToMutate) {
		this.chanceToMutate = chanceToMutate;
	}
	/**
	 * @return the populationSize
	 */
	public int getPopulationSize() {
		return populationSize;
	}
	/**
	 * @param populationSize the populationSize to set
	 */
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}
	/**
	 * @return the iterations
	 */
	public int getIterations() {
		return iterations;
	}
	/**
	 * @param iterations the iterations to set
	 */
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
}
