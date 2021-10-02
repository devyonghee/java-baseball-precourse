package baseball.domain.pitcher;

import baseball.domain.Rule;
import nextstep.utils.Randoms;

public final class Pitcher {

    private final Rule rule;

    private Pitcher(Rule rule) {
        validate(rule);
        this.rule = rule;
    }

    public static Pitcher from(Rule rule) {
        return new Pitcher(rule);
    }

    public Balls throwBalls() {
        Balls balls = new Balls(rule.getNumberCount());
        while (balls.isNotFull()) {
            balls.add(createBall(balls));
        }
        return balls;
    }

    private void validate(Rule rule) {
        if (rule == null) {
            throw new IllegalArgumentException("rule must not be null");
        }
    }

    private Ball createBall(Balls balls) {
        Ball ball;
        do {
            ball = new Ball(thinkThrownPosition());
        } while (balls.isContains(ball));
        return ball;
    }

    private int thinkThrownPosition() {
        int number;
        do {
            number = Randoms.pickNumberInRange(rule.getMinRange(), rule.getMaxRange());
        } while (rule.isOutOfRange(number));
        return number;
    }


}
