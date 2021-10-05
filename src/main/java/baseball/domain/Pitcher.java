package baseball.domain;

import baseball.domain.concept.Concept;
import baseball.domain.concept.Position;
import baseball.domain.tool.Ball;
import baseball.domain.tool.Balls;
import java.util.HashSet;

public final class Pitcher implements Player {

    private final Concept concept;

    private Pitcher(Concept concept) {
        validate(concept);
        this.concept = concept;
    }

    public static Pitcher from(Concept concept) {
        return new Pitcher(concept);
    }

    public Balls throwBalls() {
        HashSet<Ball> ballList = new HashSet<>();
        int index = 0;
        for (Position position : concept.thinkPositions()) {
            ballList.add(Ball.of(index++, position));
        }
        return Balls.from(ballList);
    }

    private void validate(Concept concept) {
        if (concept == null) {
            throw new IllegalArgumentException("concept must not be null");
        }
    }
}
