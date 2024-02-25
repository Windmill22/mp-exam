package project.toti.andrea.vehicle;

public class MockVehicleInformationPrinter implements VehicleInformationPrinter {

	private final StringBuilder builder = new StringBuilder();

	@Override
	public void print(String info) {
		builder.append(info + "\n");
	}

	@Override
	public String toString() {
		return builder.toString();
	}
}
