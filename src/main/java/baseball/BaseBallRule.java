package baseball;

public final class BaseBallRule implements Rule {

    private final int numberCount;
    private final Range range;

    private BaseBallRule(int numberCount, Range range) {
        validate(numberCount, range);
        this.numberCount = numberCount;
        this.range = range;
    }

    public static BaseBallRule of(int numberCount, Range range) {
        return new BaseBallRule(numberCount, range);
    }

    public int getNumberCount() {
        return numberCount;
    }

    public boolean isDifferentNumberCountFrom(int count) {
        return numberCount != count;
    }

    public boolean isOutOfRange(int number) {
        return range.isOut(number);
    }

    public int getMinRange() {
        return range.getMin();
    }

    public int getMaxRange() {
        return range.getMax();
    }

    private void validate(int numberCount, Range range) {
        if (range == null) {
            throw new IllegalArgumentException("range must not be null");
        }
        if (numberCount <= 0) {
            throw new IllegalArgumentException(String.format("numberCount must be greater than 0, but provided %d", numberCount));
        }
        if (range.size() < numberCount) {
            throw new IllegalArgumentException(String.format("Range size(%d) is too small than numberCount(%d)", range.size(), numberCount));
        }
    }
}
