package project.toti.andrea.vehicle;

import project.toti.andrea.parts.PartsFactory;

public final class PetrolVehicle extends AbstractVehicle {

	private int emission;
	public static final int ECO_TAX = 1100;

	public PetrolVehicle(PartsFactory factory, EuroStandard standard, int emission) {
		super(factory, standard);
		this.emission = emission;
	}

	@Override
	public double computePurchasePrice() {
		double oldPrice = super.computePurchasePrice();
		if (emission > 160) {
			return oldPrice + ECO_TAX;
		}
		return oldPrice;
	}

	@Override
	public void accept(VehicleVisitor visitor) {
		visitor.visitPetrolVehicle(this);
	}

	public int getEmission() {
		return emission;
	}
}
