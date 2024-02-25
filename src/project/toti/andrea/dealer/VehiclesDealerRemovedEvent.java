package project.toti.andrea.dealer;

import project.toti.andrea.vehicle.AbstractVehicle;

public class VehiclesDealerRemovedEvent extends VehiclesDealerEvent {

	public VehiclesDealerRemovedEvent(VehiclesDealer dealer, AbstractVehicle vehicle) {
		super(dealer, vehicle);
	}

	@Override
	public void accept(VehiclesDealerEventVisitor visitor) {
		visitor.visitRemoved(this);
	}

}
