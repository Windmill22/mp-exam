package project.toti.andrea.vehicle;

import project.toti.andrea.dealer.MonthlyContractStrategy;

public class VehicleSalesInformation extends VehicleInformationDecorator {

	private MonthlyContractStrategy strategy;
	private int months;

	public VehicleSalesInformation(VehicleInformation component, MonthlyContractStrategy strategy, int months) {
		super(component);
		this.strategy = strategy;
		this.months = months;
	}

	@Override
	public void showInfo(AbstractVehicle vehicle, VehicleInformationPrinter printer) {
		super.showInfo(vehicle, printer);
		printer.print("Purchase price: " + vehicle.computePurchasePrice() 
					+ "\nMonthly tax: " + vehicle.computeMonthlyTax() 
					+ "\nMonthly amount to pay for " + months + " months: "
					+ strategy.getMonthlyPayment(vehicle, months));
	}
}
