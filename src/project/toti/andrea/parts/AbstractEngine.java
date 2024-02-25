package project.toti.andrea.parts;

public abstract class AbstractEngine {

	private double initialPrice;
	private int power;

	protected AbstractEngine(double initialPrice, int power) {
		this.initialPrice = initialPrice;
		this.power = power;
	}

	public abstract double getFinalPrice();

	public int getPower() {
		return power;
	}

	protected double getInitialPrice() {
		return initialPrice;
	}
}
