package project.toti.andrea.vehicle;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;

import project.toti.andrea.dealer.MonthlyContractStrategy;
import project.toti.andrea.dealer.RentingStrategy;
import project.toti.andrea.parts.CarPartsFactory;

public class VehicleInformationTest {

	private MockVehicleInformationPrinter printer;
	private PlugInHybridVehicle vehicle;
	private static final double AVERAGE_EMISSION = 116.3;

	@Before
	public void setUp() {
		printer = new MockVehicleInformationPrinter();
		vehicle = new PlugInHybridVehicle(
					new CarPartsFactory(1000, 1000, 5, 1000, 100), EuroStandard.EURO_6, 10);
	}

	@Test
	public void testNullComponent() {
		assertThatThrownBy(() -> new VehicleColorInformation(null, "Red"))
				.isInstanceOf(NullPointerException.class)
				.hasMessage("Component cannot be null");
	}

	@Test
	public void testShowInfoTypePlugInHybridVehicleVehicle() {
		new VehicleTypeInformation(AVERAGE_EMISSION).showInfo(vehicle, printer);
		assertThat(printer.toString())
				.isEqualTo(
					"Plug-in hybrid vehicle\n" + "Discount: " + vehicle.getDiscount() + "\n");
	}

	@Test
	public void testShowInfoTypePetrolVehicle() {
		PetrolVehicle petrolVehicle = new PetrolVehicle(
					new CarPartsFactory(100, 100, 100, 100, 100), EuroStandard.EURO_6, 10);
		new VehicleTypeInformation(AVERAGE_EMISSION).showInfo(petrolVehicle, printer);
		assertThat(printer.toString())
				.isEqualTo("Petrol vehicle\nEmissions: " + petrolVehicle.getEmission()
						+ " g/km. Average emissions: " + AVERAGE_EMISSION + " g/km\n");
	}

	@Test
	public void testShowInfoTypeAndSales() {
		final int MONTHS = 10;
		MonthlyContractStrategy strategy = new RentingStrategy(10);
		new VehicleSalesInformation(
				new VehicleTypeInformation(AVERAGE_EMISSION), strategy, MONTHS)
		.showInfo(vehicle, printer);
		assertThat(printer.toString())
			.isEqualTo(
					"Plug-in hybrid vehicle\nDiscount: " + vehicle.getDiscount()
					+ "\nPurchase price: " + vehicle.computePurchasePrice() 
					+ "\nMonthly tax: "+ vehicle.computeMonthlyTax() 
					+ "\nMonthly amount to pay for " + MONTHS + " months: "
					+ strategy.getMonthlyPayment(vehicle, MONTHS) + "\n");
	}

	@Test
	public void testShowInfoTypeAndColor() {
		new VehicleColorInformation(
				new VehicleTypeInformation(AVERAGE_EMISSION),
					"Red")
			.showInfo(vehicle, printer);
		assertThat(printer.toString())
			.isEqualTo(
					"Plug-in hybrid vehicle\nDiscount: " + vehicle.getDiscount() 
					+ "\nColor: Red\n");
	}

	@Test
	public void testShowInfoTypeAndColorNotAvailableColor() {
		new VehicleColorInformation(
				new VehicleTypeInformation(
						AVERAGE_EMISSION),
				"Black")
			.showInfo(vehicle, printer);
		assertThat(printer.toString())
			.isEqualTo(
					"Plug-in hybrid vehicle\nDiscount: " + vehicle.getDiscount()
					+ "\nColor: Black(not available at the moment)\n");
	}

	@Test
	public void testShowInfoTypeAndColorLowerCaseColor() {
		new VehicleColorInformation(
				new VehicleTypeInformation(AVERAGE_EMISSION),
				"red")
			.showInfo(vehicle, printer);
		assertThat(printer.toString())
				.isEqualTo(
					"Plug-in hybrid vehicle\nDiscount: " + vehicle.getDiscount() 
					+ "\nColor: red\n");
	}

	@Test
	public void testShowInfoTypeColorAndSales() {
		final int MONTHS = 12;
		MonthlyContractStrategy strategy = new RentingStrategy(10);
		new VehicleSalesInformation(
				new VehicleColorInformation(
						new VehicleTypeInformation(AVERAGE_EMISSION), 
						"Red"),
				strategy, MONTHS).showInfo(vehicle, printer);
		assertThat(printer.toString()).isEqualTo(
					"Plug-in hybrid vehicle\n" + "Discount: " + vehicle.getDiscount()
					+ "\nColor: Red" 
					+ "\nPurchase price: " + vehicle.computePurchasePrice() 
					+ "\nMonthly tax: "	+ vehicle.computeMonthlyTax() 
					+ "\nMonthly amount to pay for 12 months: "
					+ strategy.getMonthlyPayment(vehicle, MONTHS) + "\n");
	}
}
