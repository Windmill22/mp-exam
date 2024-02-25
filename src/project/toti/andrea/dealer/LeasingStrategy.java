package project.toti.andrea.dealer;

import project.toti.andrea.vehicle.AbstractVehicle;

public class LeasingStrategy implements MonthlyContractStrategy {

	private double insuranceMonthlyPrice;
	private int prepaymentPercentage;

	public LeasingStrategy(double insuranceMonthlyPrice, int prepaymentPercentage) {
		this.insuranceMonthlyPrice = insuranceMonthlyPrice;
		this.prepaymentPercentage = prepaymentPercentage;
	}

	@Override
	public double getMonthlyPayment(AbstractVehicle vehicle, int months) {
		if (months <= 1) {
			throw new IllegalArgumentException("Expected months to be greater than 1, but was: " + months);
		}
		double price = vehicle.computePurchasePrice();
		double prepayment = price * (prepaymentPercentage / 100.0);
		return (price - prepayment) / months + insuranceMonthlyPrice + vehicle.computeMonthlyTax();
	}
}
