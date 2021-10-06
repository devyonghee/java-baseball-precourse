package baseball.model.concept;

import java.util.Objects;

public final class Position {

	private final int value;

	private Position(int value) {
		this.value = value;
	}

	public static Position from(int pickNumber) {
		return new Position(pickNumber);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Position position = (Position)o;
		return value == position.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	public boolean notEquals(Position position) {
		return !equals(position);
	}
}
