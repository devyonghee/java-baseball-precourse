package baseball.controller;

import baseball.BaseBallRule;
import baseball.controller.dto.Score;
import baseball.domain.Batter;
import baseball.domain.Pitcher;
import baseball.domain.concept.ConceptGuideDecorator;
import baseball.domain.concept.RandomConcept;
import baseball.domain.concept.UserInputConcept;
import baseball.domain.tool.Balls;
import baseball.domain.tool.HitBalls;
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

        Score score;
        do {
            score = hitBalls(batter, balls);
            display.exposure(score);
        } while (score.isNotFinish());
    }

    private Score hitBalls(Batter batter, Balls balls) {
        try {
            return createScore(batter.wieldBats().hit(balls));
        } catch (IllegalArgumentException exception) {
            return Score.ERROR;
        }
    }

    private Score createScore(HitBalls hitBalls) {
        return Score.from(
                hitBalls.getStrikeCount(),
                hitBalls.getBallCount(),
                rule.isDifferentNumberCountFrom(hitBalls.getStrikeCount())
        );
    }
}
