package project.toti.andrea.dealer;

import project.toti.andrea.vehicle.AbstractVehicle;

public abstract class VehiclesDealerEvent {

	private VehiclesDealer dealer;
	private AbstractVehicle vehicle;

	protected VehiclesDealerEvent(VehiclesDealer dealer, AbstractVehicle vehicle) {
		this.dealer = dealer;
		this.vehicle = vehicle;
	}

	public VehiclesDealer getDealer() {
		return dealer;
	}

	public AbstractVehicle getVehicle() {
		return vehicle;
	}

	public abstract void accept(VehiclesDealerEventVisitor visitor);	
}
