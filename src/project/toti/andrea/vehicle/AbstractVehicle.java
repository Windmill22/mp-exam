package project.toti.andrea.vehicle;

import java.util.Objects;

import project.toti.andrea.parts.AbstractEngine;
import project.toti.andrea.parts.AbstractTire;
import project.toti.andrea.parts.PartsFactory;

public abstract class AbstractVehicle {

	private AbstractEngine engine;
	private AbstractTire tire;
	private EuroStandard standard;

	protected AbstractVehicle(PartsFactory factory, EuroStandard standard) {
		Objects.requireNonNull(factory, "factory cannot be null");
		this.standard = Objects.requireNonNull(standard, "standard cannot be null");
		tire = factory.createTire();
		engine = factory.createEngine();
	}

	public double computePurchasePrice() {
		return tire.getFullSetPrice() + engine.getFinalPrice();
	}

	public double computeMonthlyTax() {
		int power = engine.getPower();
		double pricePerKw = standard.getPricePerKw();
		if (power > 100) {
			return pricePerKw * 100 + (power - 100) * standard.getSurcharge();
		}
		return pricePerKw * power;
	}

	public abstract void accept(VehicleVisitor visitor);

	AbstractEngine getEngine() {
		return engine;
	}

	AbstractTire getTire() {
		return tire;
	}
}
