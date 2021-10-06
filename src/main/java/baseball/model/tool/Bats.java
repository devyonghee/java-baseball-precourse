package baseball.model.tool;

import java.util.ArrayList;
import java.util.Collection;

public final class Bats {

    private final Collection<Bat> batList;

    private Bats(Collection<Bat> batList) {
        validate(batList);
        this.batList = batList;
    }

    public static Bats from(Collection<Bat> batList) {
        return new Bats(batList);
    }

    /**
     * <p>공 리스트에게 상태를 받아 맞은 공들 객체를 반환합니다.</p>
     *
     * @param balls 비교할 공들
     * @return 맞은 상태를 가지고 있는 공들
     */
    public HitBalls hit(Balls balls) {
        ArrayList<HitBall> hitBalls = new ArrayList<>();
        for (Bat bat : batList) {
            hitBalls.add(bat.hit(balls));
        }
        return HitBalls.from(hitBalls);
    }

    private void validate(Collection<Bat> batList) {
        if (batList == null) {
            throw new IllegalArgumentException("batList must not be null");
        }
    }
}
