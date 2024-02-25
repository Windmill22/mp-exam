package project.toti.andrea.dealer;

public interface VehiclesDealerEventVisitor {

	void visitAdded(VehiclesDealerAddedEvent event);

	void visitRemoved(VehiclesDealerRemovedEvent event);

}
