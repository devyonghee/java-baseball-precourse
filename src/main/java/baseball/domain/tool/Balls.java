package baseball.domain.tool;

import baseball.domain.concept.Position;
import java.util.Collection;

public final class Balls {

    private final Collection<Ball> ballList;

    private Balls(Collection<Ball> ballList) {
        validate(ballList);
        this.ballList = ballList;
    }

    public static Balls from(Collection<Ball> ballList) {
        return new Balls(ballList);
    }

    public HitStatus hit(int hitIndex, Position position) {
        HitStatus status = HitStatus.NOT;
        for (Ball ball : ballList) {
            status = HitStatus.higherPriority(ball.getHitStatus(hitIndex, position), status);
        }
        return status;
    }

    private void validate(Collection<Ball> ballList) {
        if (ballList == null) {
            throw new IllegalArgumentException("ballList must not be null");
        }
    }
}
