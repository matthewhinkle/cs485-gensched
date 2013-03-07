package src.optimizer;


public interface Heuristic<T extends Evolvable<T>> {
	public Integer getValue(T evolvable);
}
