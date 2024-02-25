package project.toti.andrea.vehicle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;

import project.toti.andrea.parts.CarPartsFactory;
import project.toti.andrea.parts.PartsFactory;

public class PetrolVehicleTest {

	private PartsFactory factory;
	private static final EuroStandard STANDARD = EuroStandard.EURO_6;

	@Before
	public void setUp() {
		factory = new CarPartsFactory(20.3, 11.6, 5, 6.3, 200);
	}

	@Test
	public void testConstructorStandardIsNull() {
		assertThatThrownBy(() -> new PetrolVehicle(factory, null, 100))
				.isInstanceOf(NullPointerException.class)
				.hasMessage("standard cannot be null");
	}

	@Test
	public void testConstructorFactoryIsNull() {
		assertThatThrownBy(() -> new PetrolVehicle(null, STANDARD, 100))
				.isInstanceOf(NullPointerException.class)
				.hasMessage("factory cannot be null");
	}

	@Test
	public void testPurchasePriceEmissionGreaterThan160() {
		final int EMISSION = 165;
		AbstractVehicle vehicle = new PetrolVehicle(factory, STANDARD, EMISSION);
		assertThat(vehicle.computePurchasePrice())
			.isEqualTo(
				vehicle.getEngine().getFinalPrice() + vehicle.getTire().getFullSetPrice() + 1100);
	}

	@Test
	public void testPurchasePriceEmissionIsEqualTo160() {
		final int EMISSION = 160;
		AbstractVehicle vehicle = new PetrolVehicle(factory, STANDARD, EMISSION);
		assertThat(vehicle.computePurchasePrice())
			.isEqualTo(
				vehicle.getEngine().getFinalPrice() + vehicle.getTire().getFullSetPrice());
	}

	@Test
	public void testPurchasePriceEmissionLessThan160() {
		final int EMISSION = 130;
		AbstractVehicle vehicle = new PetrolVehicle(factory, STANDARD, EMISSION);
		assertThat(vehicle.computePurchasePrice())
			.isEqualTo(
				vehicle.getEngine().getFinalPrice() + vehicle.getTire().getFullSetPrice());
	}

	@Test
	public void testMonthlyTaxPowerGreaterThan100() {
		final int POWER = 250;
		AbstractVehicle vehicle = new PetrolVehicle(
				new CarPartsFactory(20.3, 11.6, 5, 6.3, POWER), STANDARD, 55);
		double expectedResult = STANDARD.getPricePerKw() * 100 + (POWER - 100) * STANDARD.getSurcharge();
		assertThat(vehicle.computeMonthlyTax()).isEqualTo(expectedResult);
	}

	@Test
	public void testMonthlyTaxPowerEqualTo100() {
		final int POWER = 100;
		AbstractVehicle vehicle = new PetrolVehicle(
				new CarPartsFactory(20.3, 11.6, 5, 6.3, POWER), STANDARD, 55);
		double expectedResult = STANDARD.getPricePerKw() * POWER;
		assertThat(vehicle.computeMonthlyTax()).isEqualTo(expectedResult);
	}

	@Test
	public void testMonthlyTaxPowerLessThan100() {
		final int POWER = 75;
		AbstractVehicle vehicle = new PetrolVehicle(
				new CarPartsFactory(20.3, 11.6, 5, 6.3, POWER), STANDARD, 55);
		double expectedResult = STANDARD.getPricePerKw() * POWER;
		assertThat(vehicle.computeMonthlyTax()).isEqualTo(expectedResult);
	}
}
