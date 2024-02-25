package project.toti.andrea.observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import project.toti.andrea.dealer.VehiclesDealer;
import project.toti.andrea.dealer.VehiclesDealerAddedEvent;
import project.toti.andrea.dealer.VehiclesDealerEvent;
import project.toti.andrea.dealer.VehiclesDealerEventVisitor;
import project.toti.andrea.dealer.VehiclesDealerObserver;
import project.toti.andrea.dealer.VehiclesDealerRemovedEvent;
import project.toti.andrea.vehicle.AbstractVehicle;

public class VehiclesDealerPotentialBuyerObserver implements VehiclesDealerObserver {

	private double budget;
	private Collection<AbstractVehicle> affordableVehicles = new ArrayList<>();

	public VehiclesDealerPotentialBuyerObserver(double budget, VehiclesDealer dealer) {
		Objects.requireNonNull(dealer, "dealer cannot be null");
		this.budget = budget;
		dealer.addObserver(this);
	}

	@Override
	public void update(VehiclesDealerEvent event) {
		event.accept(new VehiclesDealerEventVisitor() {

			@Override
			public void visitRemoved(VehiclesDealerRemovedEvent event) {
				affordableVehicles.remove(event.getVehicle());
			}

			@Override
			public void visitAdded(VehiclesDealerAddedEvent event) {
				if (budget >= event.getVehicle().computePurchasePrice()) {
					affordableVehicles.add(event.getVehicle());
				}
			}
		});
	}

	Collection<AbstractVehicle> getAffordableVehicles() {
		return affordableVehicles;
	}

}
