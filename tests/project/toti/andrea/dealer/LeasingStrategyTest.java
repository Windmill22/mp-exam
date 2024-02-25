package project.toti.andrea.dealer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;

import project.toti.andrea.parts.CarPartsFactory;
import project.toti.andrea.vehicle.EuroStandard;
import project.toti.andrea.vehicle.PlugInHybridVehicle;

public class LeasingStrategyTest {

	private PlugInHybridVehicle vehicle;
	private LeasingStrategy strategy;
	private static final double INSURANCE_MONTHLY_PRICE = 120.2;
	private static final int PREPAYMENT_PERCENTAGE = 30;

	@Before
	public void setUp() {
		vehicle = new PlugInHybridVehicle(
				new CarPartsFactory(100, 100, 100, 100, 100), EuroStandard.EURO_6, 50);
		strategy = new LeasingStrategy(INSURANCE_MONTHLY_PRICE, PREPAYMENT_PERCENTAGE);
	}

	@Test
	public void testGetMonthlyPayment() {
		final int MONTHS = 12;
		double purchasePrice = vehicle.computePurchasePrice();
		double monthlyTax = vehicle.computeMonthlyTax();
		double prepayment = purchasePrice * (PREPAYMENT_PERCENTAGE / 100.0);
		double expectedResult = 
				(purchasePrice - prepayment) / MONTHS + INSURANCE_MONTHLY_PRICE + monthlyTax;
		assertThat(strategy.getMonthlyPayment(vehicle, MONTHS)).isEqualTo(expectedResult);
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
