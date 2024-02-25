package project.toti.andrea.parts;

public abstract class AbstractTire {

	private double initialPrice;

	protected AbstractTire(double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public abstract double getFullSetPrice();

	protected double getInitialPrice() {
		return initialPrice;
	}
}
