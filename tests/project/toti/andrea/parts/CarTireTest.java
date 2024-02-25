package project.toti.andrea.parts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CarTireTest {

	@Test
	public void testFullSetPrice() {
		final double INITIAL_PRICE = 80.6;
		assertThat(new CarTire(INITIAL_PRICE)
				.getFullSetPrice())
		.isEqualTo(4 * INITIAL_PRICE);
	}
}
