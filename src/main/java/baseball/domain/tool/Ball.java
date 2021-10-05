package baseball.domain.tool;

import baseball.domain.concept.Position;

public final class Ball {

    private final int index;
    private final Position position;

    private Ball(int index, Position position) {
        validate(position);
        this.index = index;
        this.position = position;
    }

    public static Ball of(int index, Position position) {
        return new Ball(index, position);
    }

    HitStatus getHitStatus(int hitIndex, Position position) {
        return HitStatus.valueOf(this.position.notEquals(position), hitIndex != index);
    }

    private void validate(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("position must not be null");
        }
    }
}
