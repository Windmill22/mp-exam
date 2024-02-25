package project.toti.andrea.observer;

import org.junit.Before;

import org.junit.Test;

import project.toti.andrea.dealer.RentingStrategy;
import project.toti.andrea.dealer.VehicleNotFoundException;
import project.toti.andrea.dealer.VehiclesDealer;
import project.toti.andrea.parts.CarPartsFactory;
import project.toti.andrea.vehicle.AbstractVehicle;
import project.toti.andrea.vehicle.EuroStandard;
import project.toti.andrea.vehicle.PlugInHybridVehicle;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class VehiclesDealerPotentialBuyerObserverTest {

	private VehiclesDealer dealer;
	private AbstractVehicle vehicle;

	@Before
	public void setUp() {
		vehicle = new PlugInHybridVehicle(
						new CarPartsFactory(1000, 1000, 5, 1000, 200), EuroStandard.EURO_6, 10);
		dealer = new VehiclesDealer(new RentingStrategy(100));
	}

	@Test
	public void testConstructorNullDealer() {
		assertThatThrownBy(() -> new VehiclesDealerPotentialBuyerObserver(1000, null))
		.isInstanceOf(NullPointerException.class)
		.hasMessage("dealer cannot be null");
	}

	@Test
	public void testAddedVehicleBudgetLessThanPurchasePrice() {
		double budget = vehicle.computePurchasePrice() - 10;
		VehiclesDealerPotentialBuyerObserver observer = new VehiclesDealerPotentialBuyerObserver(budget, dealer);
		dealer.addVehicle(vehicle);
		assertThat(observer.getAffordableVehicles()).isEmpty();
	}

	@Test
	public void testAddedVehicleBudgetIsEqualToPurchasePrice() {
		double budget = vehicle.computePurchasePrice();
		VehiclesDealerPotentialBuyerObserver observer = new VehiclesDealerPotentialBuyerObserver(budget, dealer);
		dealer.addVehicle(vehicle);
		assertThat(observer.getAffordableVehicles()).hasSize(1);
		assertThat(observer.getAffordableVehicles()).containsExactly(vehicle);
	}

	@Test
	public void testAddedVehicleBudgetGreaterThanPurchasePrice() {
		double budget = vehicle.computePurchasePrice() + 10;
		VehiclesDealerPotentialBuyerObserver observer = new VehiclesDealerPotentialBuyerObserver(budget, dealer);
		dealer.addVehicle(vehicle);
		assertThat(observer.getAffordableVehicles()).hasSize(1);
		assertThat(observer.getAffordableVehicles()).containsExactly(vehicle);
	}

	@Test
	public void testRemovedVehicle() throws VehicleNotFoundException {
		VehiclesDealerPotentialBuyerObserver observer = new VehiclesDealerPotentialBuyerObserver(10, dealer);
		observer.getAffordableVehicles().add(vehicle);
		dealer.addVehicle(vehicle);
		dealer.sellVehicle(vehicle.computePurchasePrice() + 10);
		assertThat(observer.getAffordableVehicles()).isEmpty();
	}
}
