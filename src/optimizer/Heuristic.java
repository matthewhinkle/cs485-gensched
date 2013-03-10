package src.optimizer;


public interface Heuristic<T extends Evolvable<T>> {
	public Double getValue(T evolvable);
}
