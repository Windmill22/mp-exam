package project.toti.andrea.visitor;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Before;
import org.junit.Test;

import project.toti.andrea.parts.CarPartsFactory;
import project.toti.andrea.vehicle.EuroStandard;
import project.toti.andrea.vehicle.MockVehicleInformationPrinter;
import project.toti.andrea.vehicle.PetrolVehicle;
import project.toti.andrea.vehicle.PlugInHybridVehicle;
import project.toti.andrea.vehicle.VehicleVisitor;


public class VehicleInformationVisitorTest {

	private MockVehicleInformationPrinter printer;
	private VehicleVisitor visitor;
	private static final double AVERAGE_EMISSION = 98.6;

	@Before
	public void setUp() {
		printer = new MockVehicleInformationPrinter();
		visitor = new VehicleInformationVisitor(printer, AVERAGE_EMISSION);
	}

	@Test
	public void testPetrolVehicleInformation() {
		final int EMISSION = 10;
		new PetrolVehicle(
				new CarPartsFactory(100, 100, 2, 100, 100), EuroStandard.EURO_6, EMISSION)
		.accept(visitor);
		assertThat(printer.toString())
			.isEqualTo(
				"Petrol vehicle\n" + "Emissions: " + EMISSION
				+ " g/km. Average emissions: " + AVERAGE_EMISSION + " g/km\n");
	}

	@Test
	public void testHybridVehicleInformation() {
		final double DISCOUNT = 32.2;
		new PlugInHybridVehicle(
				new CarPartsFactory(100, 100, 2, 100, 100), EuroStandard.EURO_6, DISCOUNT)
		.accept(visitor);
		assertThat(printer.toString())
			.isEqualTo(
				"Plug-in hybrid vehicle\n" + "Discount: " + DISCOUNT + "\n");
	}
}
