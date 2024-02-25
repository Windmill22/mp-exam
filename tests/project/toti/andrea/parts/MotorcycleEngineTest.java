package project.toti.andrea.parts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class MotorcycleEngineTest {

	private static final double INITIAL_PRICE = 30.8;

	@Test
	public void testTotalPriceSizeLessThan600() {
		final int SIZE = 350;
		assertThat(new MotorcycleEngine(INITIAL_PRICE, 10, SIZE)
				.getFinalPrice())
		.isEqualTo(INITIAL_PRICE);
	}

	@Test
	public void testTotalPriceSizeIsEqualTo600() {
		final int SIZE = 600;
		assertThat(new MotorcycleEngine(INITIAL_PRICE, 10, SIZE)
				.getFinalPrice())
		.isEqualTo(INITIAL_PRICE);
	}

	@Test
	public void testTotalPriceSizeGreaterThan600() {
		final int SIZE = 700;
		assertThat(new MotorcycleEngine(INITIAL_PRICE, 10, SIZE)
				.getFinalPrice())
		.isEqualTo(INITIAL_PRICE * 1.1);
	}
}
