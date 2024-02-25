package project.toti.andrea.parts;

public class CarTire extends AbstractTire {

	public CarTire(double initialPrice) {
		super(initialPrice);
	}
 
	@Override
	public double getFullSetPrice() {
		return 4 * getInitialPrice();
	}
}
