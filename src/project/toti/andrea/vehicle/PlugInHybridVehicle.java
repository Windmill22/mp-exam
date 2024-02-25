package project.toti.andrea.vehicle;

import project.toti.andrea.parts.PartsFactory;

public final class PlugInHybridVehicle extends AbstractVehicle {

	private double discount;

	public PlugInHybridVehicle(PartsFactory factory, EuroStandard standard, double discount) {
		super(factory, standard);
		this.discount = discount;
	}

	@Override
	public double computePurchasePrice() {
		return super.computePurchasePrice() - discount;
	}

	@Override
	public void accept(VehicleVisitor visitor) {
		visitor.visitPlugInHybridVehicle(this);
	}

	public double getDiscount() {
		return discount;
	}
}
