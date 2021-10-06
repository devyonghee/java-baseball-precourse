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
	public boolean equals(Object target) {
		if (this == target) {
			return true;
		}
		if (target == null || getClass() != target.getClass()) {
			return false;
		}
		Position position = (Position)target;
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
