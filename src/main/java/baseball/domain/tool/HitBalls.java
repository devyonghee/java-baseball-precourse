package baseball.domain.tool;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

public final class HitBalls {

    private final Collection<HitBall> hitBallList;
    private final Map<HitStatus, Integer> counts = new EnumMap<>(HitStatus.class);

    private HitBalls(Collection<HitBall> hitBallList) {
        validate(hitBallList);
        this.hitBallList = hitBallList;
    }

    public static HitBalls from(Collection<HitBall> hitBalls) {
        return new HitBalls(hitBalls);
    }

    public int getStrikeCount() {
        checkStatus();
        return counts.get(HitStatus.STRIKE);
    }

    public int getBallCount() {
        checkStatus();
        return counts.get(HitStatus.BALL);
    }

    private void validate(Collection<HitBall> hitBallList) {
        if (hitBallList == null) {
            throw new IllegalArgumentException("hitBallList must not be null");
        }
    }

    private void checkStatus() {
        if (counts.isEmpty()) {
            calculate();
        }
    }

    private void calculate() {
        initCounts();
        countStatuses();
    }

    private void initCounts() {
        for (HitStatus status : HitStatus.values()) {
            counts.putIfAbsent(status, 0);
        }
    }

    private void countStatuses() {
        for (HitBall ball : hitBallList) {
            counts.computeIfPresent(ball.getStatus(), (key, count) -> count + 1);
        }
    }

}
