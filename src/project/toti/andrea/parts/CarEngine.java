
package project.toti.andrea.parts;

public class CarEngine extends AbstractEngine {

	private double cylinderPrice;
	private int cylindersNumber;

	public CarEngine(double initialPrice, int power, double cylinderPrice, int cylinderNumber) {
		super(initialPrice, power);
		this.cylinderPrice = cylinderPrice;
		this.cylindersNumber = cylinderNumber;
	}

	@Override
	public double getFinalPrice() {
		return getInitialPrice() + (cylindersNumber * cylinderPrice);
	}
}
