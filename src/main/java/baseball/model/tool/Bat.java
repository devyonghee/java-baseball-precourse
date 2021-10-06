package baseball.model.tool;

import baseball.model.concept.Position;

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

    /**
     * <p>공 리스트에게 상태를 받아 맞은 공 객체를 반환합니다.</p>
     *
     * @param balls 비교할 공들
     * @return 상태를 관리하는 맞은 공 반환
     */
    HitBall hit(Balls balls) {
        return HitBall.from(balls.hit(index, position));
    }

    private void validate(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("position must not be null");
        }
    }
}
