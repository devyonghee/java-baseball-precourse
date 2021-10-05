package baseball.domain.tool;

import baseball.domain.concept.Position;

public final class Bat {

    private final int index;
    private final Position position;

    private Bat(int index, Position position) {
        validate(position);
        this.index = index;
        this.position = position;
    }

    public static Bat of(int index, Position position) {
        return new Bat(index, position);
    }

    HitBall hit(Balls balls) {
        return HitBall.from(balls.hit(index, position));
    }

    private void validate(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("position must not be null");
        }
    }
}
