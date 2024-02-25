package project.toti.andrea.parts;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class CarEngineTest {

	@Test
	public void testFinalPrice() {
		final double INITIAL_PRICE = 20.5;
		final double CYLINDER_PRICE = 6.0;
		final int CYLINDERS_NUMBER = 3;
		double expectedFinalPrice = INITIAL_PRICE + (CYLINDERS_NUMBER * CYLINDER_PRICE);
		assertThat(new CarEngine(INITIAL_PRICE, 10, CYLINDER_PRICE, CYLINDERS_NUMBER)
				.getFinalPrice())
				.isEqualTo(expectedFinalPrice);
	}
}
