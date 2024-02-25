package project.toti.andrea.vehicle;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import project.toti.andrea.parts.MotorcyclePartsFactory;
import project.toti.andrea.parts.PartsFactory;
import project.toti.andrea.parts.SpeedRating;

public class PlugInHybridVehicleTest {

	@Test
	public void testPurchasePrice() {
		final double DISCOUNT = 100;
		PartsFactory factory = new MotorcyclePartsFactory(13.6, 25, 7.2, SpeedRating.Y, 150);
		AbstractVehicle vehicle = new PlugInHybridVehicle(factory, EuroStandard.EURO_6, DISCOUNT);
		assertThat(vehicle.computePurchasePrice())
		.isEqualTo(
			vehicle.getEngine().getFinalPrice() + vehicle.getTire().getFullSetPrice() - DISCOUNT);
	}
}
