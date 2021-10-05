package baseball.controller.dto;

public final class Score {

    public static final Score ERROR = new Score(0, 0, true, true);

    private final int strikeCount;
    private final int ballCount;
    private final boolean isNotFinished;
    private final boolean hasError;

    private Score(int strikeCount, int ballCount, boolean isNotFinished, boolean hasError) {
        this.strikeCount = strikeCount;
        this.ballCount = ballCount;
        this.isNotFinished = isNotFinished;
        this.hasError = hasError;
    }

    private Score(int strikeCount, int ballCount, boolean isNotFinished) {
        this(strikeCount, ballCount, isNotFinished, false);
    }

    public static Score from(int strikeCount, int ballCount, boolean isNotFinished) {
        return new Score(strikeCount, ballCount, isNotFinished);
    }

    public boolean isNotFinish() {
        return isNotFinished;
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

    public boolean hasError() {
        return hasError;
    }

    public boolean isNothing() {
        return hitCount() == 0;
    }

    private int hitCount() {
        return strikeCount + ballCount;
    }
}
