package project.toti.andrea.dealer;

import project.toti.andrea.vehicle.AbstractVehicle;

public class RentingStrategy implements MonthlyContractStrategy {

	private double supplement;

	public RentingStrategy(double supplement) {
		this.supplement = supplement;
	}

	@Override
	public double getMonthlyPayment(AbstractVehicle vehicle, int months) {
		if (months <= 1) {
			throw new IllegalArgumentException("Expected months to be greater than 1, but was: " + months);
		}
		return vehicle.computePurchasePrice() / months - vehicle.computeMonthlyTax() + supplement;
	}
}
