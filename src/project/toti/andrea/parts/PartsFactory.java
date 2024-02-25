package project.toti.andrea.parts;

public abstract class PartsFactory {

	private double engineInitialPrice;
	private double tireInitialPrice;
	private int power;

	protected PartsFactory(double engineInitialPrice, double tireInitialPrice, int power) {
		this.engineInitialPrice = engineInitialPrice;
		this.tireInitialPrice = tireInitialPrice;
		this.power = power;
	}

	public abstract AbstractEngine createEngine();

	public abstract AbstractTire createTire();

	protected double getEngineInitialPrice() {
		return engineInitialPrice;
	}

	protected double getTireInitialPrice() {
		return tireInitialPrice;
	}

	protected int getPower() {
		return power;
	}
}