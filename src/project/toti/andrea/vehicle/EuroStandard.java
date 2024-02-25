package project.toti.andrea.vehicle;

public enum EuroStandard {
	EURO_6(2.58, 3.87), EURO_3(2.70, 4.05);

	private final double pricePerKw;
	private final double surcharge;

	EuroStandard(double pricePerKw, double surcharge) {
		this.pricePerKw = pricePerKw;
		this.surcharge = surcharge;
	}

	public double getPricePerKw() {
		return pricePerKw;
	}

	public double getSurcharge() {
		return surcharge;
	}
}
