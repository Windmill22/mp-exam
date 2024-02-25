package project.toti.andrea.dealer;

import org.junit.Before;
import org.junit.Test;

import project.toti.andrea.parts.CarPartsFactory;
import project.toti.andrea.vehicle.EuroStandard;
import project.toti.andrea.vehicle.PlugInHybridVehicle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RentingStrategyTest {

	private PlugInHybridVehicle vehicle;
	private RentingStrategy strategy;
	private static final double SUPPLEMENT = 50.3;

	@Before
	public void setUp() {
		vehicle = new PlugInHybridVehicle(
				new CarPartsFactory(1000, 1000, 100, 1000, 100), EuroStandard.EURO_6, 50);
		strategy = new RentingStrategy(SUPPLEMENT);
	}

	@Test
	public void testGetMonthlyPayment() {
		final int MONTHS = 12;
		double purchasePrice = vehicle.computePurchasePrice();
		double monthlyTax = vehicle.computeMonthlyTax();
		assertThat(strategy.getMonthlyPayment(vehicle, MONTHS))
				.isEqualTo(purchasePrice / MONTHS - monthlyTax + SUPPLEMENT);
	}

	@Test
	public void testGetMonthlyPaymentMonthsIsEqualTo1() {
		final int MONTHS = 1;
		assertThatThrownBy(() -> strategy.getMonthlyPayment(vehicle, MONTHS))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Expected months to be greater than 1, but was: " + MONTHS);
	}

	@Test
	public void testGetMonthlyPaymentMonthsLessThan1() {
		final int MONTHS = -2;
		assertThatThrownBy(() -> strategy.getMonthlyPayment(vehicle, MONTHS))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Expected months to be greater than 1, but was: " + MONTHS);
	}
}
