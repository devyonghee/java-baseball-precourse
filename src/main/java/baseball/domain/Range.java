package baseball.domain;

public final class Range {

    private final int min;
    private final int max;

    private Range(int min, int max) {
        validate(min, max);
        this.min = min;
        this.max = max;
    }

    public static Range of(int max, int min) {
        return new Range(max, min);
    }

    public boolean isOut(int number) {
        return isLessThanMin(number) && isOverThanMax(number);
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    private boolean isOverThanMax(int number) {
        return max < number;
    }

    private boolean isLessThanMin(int number) {
        return number < min;
    }

    private void validate(int min, int max) {
        if (max < min) {
            throw new IllegalArgumentException();
        }
    }
}
