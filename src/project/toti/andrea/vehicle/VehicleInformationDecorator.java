package project.toti.andrea.vehicle;

import java.util.Objects;

public abstract class VehicleInformationDecorator implements VehicleInformation {

	private VehicleInformation component;

	protected VehicleInformationDecorator(VehicleInformation component) {
		this.component = Objects.requireNonNull(component, "Component cannot be null");
	}

	@Override
	public void showInfo(AbstractVehicle vehicle, VehicleInformationPrinter printer) {
		component.showInfo(vehicle, printer);
	}
}
