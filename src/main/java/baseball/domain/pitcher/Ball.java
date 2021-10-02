package baseball.domain.pitcher;

import java.util.Objects;

public final class Ball {

    private final int position;
    private boolean hit = false;

    public Ball(int position) {
        this.position = position;
    }

    public boolean isSamePosition(int position) {
        return this.position == position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ball ball = (Ball) o;
        return position == ball.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    private void hit() {
        this.hit = true;
    }

    private boolean isHit() {
        return hit;
    }
}
