package project.toti.andrea.parts;

public enum SpeedRating {
	Y(300), W(200), V(100);

	private final int extra;

	SpeedRating(int extra) {
		this.extra = extra;
	}

	public int getExtra() {
		return extra;
	}
}
