package project.toti.andrea.dealer;

public class MockVechiclesDealerObserver implements VehiclesDealerObserver {

	private VehiclesDealerEvent event;

	@Override
	public void update(VehiclesDealerEvent event) {
		this.event = event;
	}

	public VehiclesDealerEvent getEvent() {
		return event;
	}
}
