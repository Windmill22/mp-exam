package project.toti.andrea.vehicle;

import project.toti.andrea.visitor.VehicleInformationVisitor;

public class VehicleTypeInformation implements VehicleInformation {

	private double averageEmission;

	public VehicleTypeInformation(double averageEmission) {
		this.averageEmission = averageEmission;
	}

	@Override
	public void showInfo(AbstractVehicle vehicle, VehicleInformationPrinter printer) {
		vehicle.accept(new VehicleInformationVisitor(printer, averageEmission));
	}
}
