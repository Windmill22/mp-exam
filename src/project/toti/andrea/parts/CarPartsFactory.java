package project.toti.andrea.parts;

public class CarPartsFactory extends PartsFactory {

	private double cylinderPrice;
	private int cylindersNumber;

	public CarPartsFactory(double engineInitialPrice, double cylinderPrice, int cylindersNumber,
			double tireInitialPrice, int power) {
		super(engineInitialPrice, tireInitialPrice, power);
		this.cylinderPrice = cylinderPrice;
		this.cylindersNumber = cylindersNumber;
	}

	@Override
	public AbstractEngine createEngine() {
		return new CarEngine(getEngineInitialPrice(), getPower(), cylinderPrice, cylindersNumber);
	}

	@Override
	public AbstractTire createTire() {
		return new CarTire(getTireInitialPrice());
	}
}
