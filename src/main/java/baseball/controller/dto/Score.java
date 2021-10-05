package baseball.controller.dto;

public final class Score {

    public static final Score EMPTY = new Score(0, 0);

    private final int strikeCount;
    private final int ballCount;

    private Score(int strikeCount, int ballCount) {
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
    }

    public static Score from(int strikeCount, int ballCount) {
        return new Score(strikeCount, ballCount);
    }

    public int getBallCount() {
        return ballCount;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public boolean hasStrikeAndBall() {
        return hasStrike() && hasBall();
    }

    public boolean hasStrike() {
        return strikeCount > 0;
    }

    public boolean hasBall() {
        return ballCount > 0;
    }

    public boolean isNothing() {
        return hitCount() == 0;
    }

    private int hitCount() {
        return strikeCount + ballCount;
    }
}
