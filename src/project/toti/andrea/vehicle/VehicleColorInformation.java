package project.toti.andrea.vehicle;

import java.util.Arrays;
import java.util.Collection;

public class VehicleColorInformation extends VehicleInformationDecorator {

	private String color;
	private Collection<String> availableColors = Arrays.asList("Blue", "White", "Red");

	public VehicleColorInformation(VehicleInformation component, String color) {
		super(component);
		this.color = color;
	}

	@Override
	public void showInfo(AbstractVehicle vehicle, VehicleInformationPrinter printer) {
		super.showInfo(vehicle, printer);
		String colorInfo = "Color: " + color;
		if (availableColors.stream().anyMatch(color::equalsIgnoreCase)) {
			printer.print(colorInfo);
		} else {
			printer.print(colorInfo + "(not available at the moment)");
		}
	}
}
