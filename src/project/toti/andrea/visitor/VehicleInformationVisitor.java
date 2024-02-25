package project.toti.andrea.visitor;

import project.toti.andrea.vehicle.PetrolVehicle;
import project.toti.andrea.vehicle.PlugInHybridVehicle;
import project.toti.andrea.vehicle.VehicleInformationPrinter;
import project.toti.andrea.vehicle.VehicleVisitor;

public class VehicleInformationVisitor implements VehicleVisitor {

	private VehicleInformationPrinter printer;
	private double averageEmission;

	public VehicleInformationVisitor(VehicleInformationPrinter printer, double averageEmission) {
		this.printer = printer;
		this.averageEmission = averageEmission;
	}

	@Override
	public void visitPetrolVehicle(PetrolVehicle vehicle) {
		printer.print("Petrol vehicle\n" + "Emissions: " + vehicle.getEmission() + " g/km. " + "Average emissions: "
				+ averageEmission + " g/km");

	}

	@Override
	public void visitPlugInHybridVehicle(PlugInHybridVehicle vehicle) {
		printer.print("Plug-in hybrid vehicle\nDiscount: " + vehicle.getDiscount());
	}
}
