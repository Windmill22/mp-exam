package project.toti.andrea.parts;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MotorcycleTireTest {

	@Test
	public void testConstructorNullRating() {
		assertThatThrownBy(() -> new MotorcycleTire(10, null))
		.isInstanceOf(NullPointerException.class)
		.hasMessage("rating cannot be null");
	}

	@Test
	public void testFullSetPrice() {
		final double INITIAL_PRICE = 50.2;
		assertThat(new MotorcycleTire(INITIAL_PRICE, SpeedRating.Y)
				.getFullSetPrice())
		.isEqualTo(2 * (INITIAL_PRICE + 300));
		
		assertThat(new MotorcycleTire(INITIAL_PRICE, SpeedRating.W)
				.getFullSetPrice())
		.isEqualTo(2 * (INITIAL_PRICE + 200));
		
		assertThat(new MotorcycleTire(INITIAL_PRICE, SpeedRating.V)
				.getFullSetPrice())
		.isEqualTo(2 * (INITIAL_PRICE + 100));
	}
}
