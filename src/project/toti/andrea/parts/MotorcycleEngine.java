package project.toti.andrea.parts;

public class MotorcycleEngine extends AbstractEngine {

	private int size;

	public MotorcycleEngine(double initialPrice, int power, int size) {
		super(initialPrice, power);
		this.size = size;
	}

	@Override
	public double getFinalPrice() {
		if (size > 600) {
			return 1.1 * getInitialPrice();
		}
		return getInitialPrice();
	}
}
