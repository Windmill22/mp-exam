package project.toti.andrea.parts;

import java.util.Objects;

public class MotorcycleTire extends AbstractTire {

	private SpeedRating rating;

	public MotorcycleTire(double initialPrice, SpeedRating rating) {
		super(initialPrice);
		this.rating = Objects.requireNonNull(rating, "rating cannot be null");
	}

	@Override
	public double getFullSetPrice() {
		return 2 * (getInitialPrice() + rating.getExtra());
	}
}
