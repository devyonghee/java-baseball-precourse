package baseball.domain.pitcher;

import baseball.domain.batter.HitBall;
import java.util.Arrays;

public final class Balls {

    private final Ball[] list;
    private int index = 0;

    public Balls(int maxCount) {
        list = new Ball[maxCount];
    }

    public void getHitStatus(int index, int position) {
        if (index >= list.length) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < list.length; i++) {
            new HitBall();
        }
    }

    boolean isNotFull() {
        return !isFull();
    }

    void add(Ball ball) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        list[index++] = ball;
    }

    boolean isContains(Ball ball) {
        return Arrays.asList(list)
                .contains(ball);
    }

    boolean isFull() {
        return index >= list.length;
    }
}
