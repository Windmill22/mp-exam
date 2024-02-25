package project.toti.andrea.dealer;

public class VehicleNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public VehicleNotFoundException(double budget) {
		super("No vehicle was found that meets your budget requirements: " + budget);
	}
}
