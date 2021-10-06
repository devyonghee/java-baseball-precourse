package baseball.model.tool;

import baseball.model.concept.Position;

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

    /**
     * <p>공의 순서와 위치에 동일 여부에 따라 상태를 반환합니다.</p>
     *
     * @param hitIndex 공의 index와 비교할 값
     * @param position 공의 position 과 비교할 값
     * @return index 및 position 동일 여부에 따른 상태
     */
    HitStatus getHitStatus(int hitIndex, Position position) {
        return HitStatus.valueOf(this.position.notEquals(position), hitIndex != index);
    }

    private void validate(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("position must not be null");
        }
    }
}
