package baseball.controller;

import baseball.BaseBallRule;
import baseball.controller.dto.Score;
import baseball.model.Batter;
import baseball.model.Pitcher;
import baseball.model.concept.ConceptGuideDecorator;
import baseball.model.concept.RandomConcept;
import baseball.model.concept.UserInputConcept;
import baseball.model.tool.Balls;
import baseball.model.tool.HitBalls;
import baseball.view.Display;

public final class BaseballStadium implements Stadium {

    private static final String USER_INPUT_GUIDE = "숫자를 입력해주세요 : ";
    private final BaseBallRule rule;
    private final Display<Score> display;

    private BaseballStadium(BaseBallRule rule, Display<Score> display) {
        this.rule = rule;
        this.display = display;
    }

    public static BaseballStadium of(BaseBallRule rule, Display<Score> display) {
        return new BaseballStadium(rule, display);
    }

    @Override
    public void playBall() {
        Balls balls = Pitcher.from(RandomConcept.from(rule)).throwBalls();
        Batter batter = Batter.from(
                ConceptGuideDecorator.from(UserInputConcept.from(rule), System.out, USER_INPUT_GUIDE));

        boolean isNotAllStrike = true;
        while (isNotAllStrike) {
            isNotAllStrike = isNotAllStrike(balls, batter);
        }
    }

    private boolean isNotAllStrike(Balls balls, Batter batter) {
        try {
            HitBalls hitBalls = hitBalls(balls, batter);
            display.exposure(createScore(hitBalls));
            return rule.isDifferentNumberCountFrom(hitBalls.getStrikeCount());
        } catch (IllegalArgumentException exception) {
            display.printError();
            return true;
        }
    }

    private HitBalls hitBalls(Balls balls, Batter batter) {
        return batter.wieldBats().hit(balls);
    }

    private Score createScore(HitBalls hitBalls) {
        return Score.from(
                hitBalls.getStrikeCount(),
                hitBalls.getBallCount());
    }
}
