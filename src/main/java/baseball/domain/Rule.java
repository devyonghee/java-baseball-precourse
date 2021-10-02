package baseball.domain;

public final class Rule {

    private final int numberCounts;
    private final Range range;

    private Rule(int numberCounts, Range range) {
        this.numberCounts = numberCounts;
        this.range = range;
    }

    public static Rule of(int numberCounts, Range range) {
        return new Rule(numberCounts, range);
    }

    public int getNumberCount() {
        return numberCounts;
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
}
