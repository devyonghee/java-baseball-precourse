package baseball;

public final class Range {

	private static final int MIN_RANGE = 1;
	private static final int MAX_RANGE = 9;

	private final int min;
	private final int max;

	private Range(int min, int max) {
		validate(min, max);
		this.min = min;
		this.max = max;
	}

	public static Range of(int min, int max) {
		return new Range(min, max);
	}

	boolean isOut(int number) {
		return isLessThanMin(number) || isOverThanMax(number);
	}

	int getMin() {
		return min;
	}

	int size() {
		return max - min + 1;
	}

	int getMax() {
		return max;
	}

	private boolean isOverThanMax(int number) {
		return max < number;
	}

	private boolean isLessThanMin(int number) {
		return number < min;
	}

	private void validate(int min, int max) {
		if (min < MIN_RANGE) {
			throw new IllegalArgumentException(
				String.format("min must be greater than %d, but provided %d", MIN_RANGE, min));
		}
		if (MAX_RANGE < max) {
			throw new IllegalArgumentException(
				String.format("max must be less than %d, but provided %d", MAX_RANGE, max));
		}
		if (max < min) {
			throw new IllegalArgumentException(
				String.format("max must greater than min, but provided max(%d) and min(%d)", max, min));
		}
	}
}
