package project.toti.andrea.dealer;

import project.toti.andrea.vehicle.AbstractVehicle;

public class VehiclesDealerAddedEvent extends VehiclesDealerEvent {

	public VehiclesDealerAddedEvent(VehiclesDealer dealer, AbstractVehicle vehicle) {
		super(dealer, vehicle);
	}

	@Override
	public void accept(VehiclesDealerEventVisitor visitor) {
		visitor.visitAdded(this);
	}

}
