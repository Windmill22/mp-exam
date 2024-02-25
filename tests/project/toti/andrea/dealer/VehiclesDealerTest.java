package project.toti.andrea.dealer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.Before;
import org.junit.Test;

import project.toti.andrea.parts.CarPartsFactory;
import project.toti.andrea.vehicle.AbstractVehicle;
import project.toti.andrea.vehicle.EuroStandard;
import project.toti.andrea.vehicle.PlugInHybridVehicle;

public class VehiclesDealerTest {

	private VehiclesDealer dealer;
	private AbstractVehicle vehicle;
	private MonthlyContractStrategy strategy;
	private static final int MONTHS = 12;

	@Before
	public void setUp() {
		strategy = new RentingStrategy(100);
		dealer = new VehiclesDealer(strategy);
		vehicle = new PlugInHybridVehicle(
				new CarPartsFactory(1000, 1000, 100, 1000, 250), EuroStandard.EURO_6, 5);
	}

	@Test
	public void testConstructorNullStrategy() {
		assertThatThrownBy(() -> new VehiclesDealer(null))
		.isInstanceOf(NullPointerException.class)
		.hasMessage("strategy cannot be null");
	}

	@Test
	public void testAddVehicle() {
		dealer.addVehicle(vehicle);
		assertThat(dealer.getVehicles()).hasSize(1);
		assertThat(dealer.getVehicles()).containsExactly(vehicle);
	}

	@Test
	public void testSellVehicleBudgetGreaterThanPurchasePrice() throws VehicleNotFoundException {
		double budget = vehicle.computePurchasePrice() + 10;
		dealer.getVehicles().add(vehicle);
		assertThat(dealer.sellVehicle(budget)).isSameAs(vehicle);
		assertThat(dealer.getVehicles()).isEmpty();
	}

	@Test
	public void testSellVehicleBudgetIsEqualToPurchasePrice() throws VehicleNotFoundException {
		double budget = vehicle.computePurchasePrice();
		dealer.getVehicles().add(vehicle);
		assertThat(dealer.sellVehicle(budget)).isSameAs(vehicle);
		assertThat(dealer.getVehicles()).isEmpty();
	}

	@Test
	public void testSellVehicleBudgetLessThanPurchasePrice() {
		double budget = vehicle.computePurchasePrice() - 10;
		dealer.getVehicles().add(vehicle);
		assertThatThrownBy(() -> dealer.sellVehicle(budget))
		.isInstanceOf(VehicleNotFoundException.class)
		.hasMessage("No vehicle was found that meets your budget requirements: " + budget);
		assertThat(dealer.getVehicles()).containsExactly(vehicle);
	}

	@Test
	public void testSellVehicleCollectionOfVehiclesIsEmpty() {
		final double BUDGET = 10000;
		assertThatThrownBy(() -> dealer.sellVehicle(BUDGET))
		.isInstanceOf(VehicleNotFoundException.class)
		.hasMessage("No vehicle was found that meets your budget requirements: " + BUDGET);
	}

	@Test
	public void testRentVehicleMonthlyBudgetGreaterThanMonthlyPayment() throws VehicleNotFoundException {
		double monthlyBudget = strategy.getMonthlyPayment(vehicle, MONTHS) + 10;
		dealer.getVehicles().add(vehicle);
		assertThat(dealer.rentVehicle(monthlyBudget, MONTHS)).isSameAs(vehicle);
		assertThat(dealer.getVehicles()).isEmpty();
	}

	@Test
	public void testRentVehicleMonthlyBudgetIsEqualToMonthlyPayment() throws VehicleNotFoundException {
		double monthlyBudget = strategy.getMonthlyPayment(vehicle, MONTHS);
		dealer.getVehicles().add(vehicle);
		assertThat(dealer.rentVehicle(monthlyBudget, MONTHS)).isSameAs(vehicle);
		assertThat(dealer.getVehicles()).isEmpty();
	}

	@Test
	public void testRentVehicleMonthlyBudgetLessThanMonthlyPayment() {
		double monthlyBudget = strategy.getMonthlyPayment(vehicle, MONTHS) - 10;
		dealer.getVehicles().add(vehicle);
		assertThatThrownBy(() -> dealer.rentVehicle(monthlyBudget, MONTHS))
		.isInstanceOf(VehicleNotFoundException.class)
		.hasMessage(
				"No vehicle was found that meets your budget requirements: " + monthlyBudget);
		assertThat(dealer.getVehicles()).containsExactly(vehicle);
	}

	@Test
	public void testRentVehicleCollectionOfVehiclesIsEmpty() {
		final double MONTHLY_BUDGET = 10000;
		assertThatThrownBy(() -> dealer.rentVehicle(MONTHLY_BUDGET, MONTHS))
		.isInstanceOf(VehicleNotFoundException.class)
		.hasMessage(
				"No vehicle was found that meets your budget requirements: " + MONTHLY_BUDGET);
	}

	@Test
	public void testAddObserver() {
		VehiclesDealerObserver observer = event -> {
			// For testing
		};
		dealer.addObserver(observer);
		assertThat(dealer.getObservers()).containsExactly(observer);
	}

	@Test
	public void testRemoveObserver() {
		VehiclesDealerObserver observer = event -> {
			// For testing
		};
		dealer.getObservers().add(observer);
		dealer.removeObserver(observer);
		assertThat(dealer.getObservers()).isEmpty();
	}

	@Test
	public void testAddedEvent() {
		MockVechiclesDealerObserver observer = new MockVechiclesDealerObserver();
		dealer.getObservers().add(observer);
		dealer.addVehicle(vehicle);
		VehiclesDealerAddedEvent event = (VehiclesDealerAddedEvent) observer.getEvent();
		assertThat(event.getDealer()).isSameAs(dealer);
		assertThat(event.getVehicle()).isSameAs(vehicle);
	}

	@Test
	public void testRemovedEventSellVehicle() throws VehicleNotFoundException {
		MockVechiclesDealerObserver observer = new MockVechiclesDealerObserver();
		dealer.getVehicles().add(vehicle);
		dealer.getObservers().add(observer);
		dealer.sellVehicle(vehicle.computePurchasePrice() + 100);
		VehiclesDealerRemovedEvent event = (VehiclesDealerRemovedEvent) observer.getEvent();
		assertThat(event.getDealer()).isSameAs(dealer);
		assertThat(event.getVehicle()).isSameAs(vehicle);
	}

	@Test
	public void testRemovedEventRentVehicle() throws VehicleNotFoundException {
		MockVechiclesDealerObserver observer = new MockVechiclesDealerObserver();
		dealer.getVehicles().add(vehicle);
		dealer.getObservers().add(observer);
		dealer.rentVehicle(strategy.getMonthlyPayment(vehicle, MONTHS) + 10, MONTHS);
		VehiclesDealerRemovedEvent event = (VehiclesDealerRemovedEvent) observer.getEvent();
		assertThat(event.getDealer()).isSameAs(dealer);
		assertThat(event.getVehicle()).isSameAs(vehicle);
	}
}
