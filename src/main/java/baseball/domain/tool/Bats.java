package baseball.domain.tool;

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
