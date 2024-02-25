package project.toti.andrea.parts;

public class MotorcyclePartsFactory extends PartsFactory {

	private int engineSize;
	private SpeedRating rating;

	public MotorcyclePartsFactory(double engineInitialPrice, int engineSize, double tireInitialPrice,
			SpeedRating rating, int power) {
		super(engineInitialPrice, tireInitialPrice, power);
		this.engineSize = engineSize;
		this.rating = rating;
	}
 
	@Override
	public AbstractEngine createEngine() {
		return new MotorcycleEngine(getEngineInitialPrice(), getPower(), engineSize);
	}

	@Override
	public AbstractTire createTire() {
		return new MotorcycleTire(getTireInitialPrice(), rating);
	}
}
