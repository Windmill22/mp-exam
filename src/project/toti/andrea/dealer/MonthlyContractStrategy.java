package project.toti.andrea.dealer;

import project.toti.andrea.vehicle.AbstractVehicle;

public interface MonthlyContractStrategy {

	double getMonthlyPayment(AbstractVehicle vehicle, int months);
}
