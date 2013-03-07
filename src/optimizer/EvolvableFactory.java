package src.optimizer;

public abstract class EvolvableFactory<T extends Evolvable<T>> {
	public abstract T createEvolvable();
}
