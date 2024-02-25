package project.toti.andrea.dealer;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

import project.toti.andrea.vehicle.AbstractVehicle;

public final class VehiclesDealer {

	private MonthlyContractStrategy strategy;
	private Collection<AbstractVehicle> vehicles = new ArrayList<>();
	private Collection<VehiclesDealerObserver> observers = new ArrayList<>();

	public VehiclesDealer(MonthlyContractStrategy strategy) {
		this.strategy = Objects.requireNonNull(strategy, "strategy cannot be null");
	}

	public AbstractVehicle rentVehicle(double monthlyBudget, int months) throws VehicleNotFoundException {
		return findAffordableVehicle(vehicle -> monthlyBudget >= strategy.getMonthlyPayment(vehicle, months),
				monthlyBudget);
	}

	public AbstractVehicle sellVehicle(double budget) throws VehicleNotFoundException {
		return findAffordableVehicle(vehicle -> budget >= vehicle.computePurchasePrice(), budget);
	}

	public void addVehicle(AbstractVehicle vehicle) {
		vehicles.add(vehicle);
		notifyObservers(new VehiclesDealerAddedEvent(this, vehicle));
	}

	public void addObserver(VehiclesDealerObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(VehiclesDealerObserver observer) {
		observers.remove(observer);
	}

	Collection<AbstractVehicle> getVehicles() {
		return vehicles;
	}

	Collection<VehiclesDealerObserver> getObservers() {
		return observers;
	}

	private AbstractVehicle findAffordableVehicle(Predicate<AbstractVehicle> predicate, double budget)
			throws VehicleNotFoundException {
		AbstractVehicle vehicle = vehicles.stream().filter(predicate).findFirst()
				.orElseThrow(() -> new VehicleNotFoundException(budget));
		vehicles.remove(vehicle);
		notifyObservers(new VehiclesDealerRemovedEvent(this, vehicle));
		return vehicle;
	}

	private void notifyObservers(VehiclesDealerEvent event) {
		observers.forEach(observer -> observer.update(event));
	}
}
