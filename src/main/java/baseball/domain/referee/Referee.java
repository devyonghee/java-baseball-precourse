package baseball.domain.referee;

import baseball.domain.Rule;
import baseball.controller.dto.Score;
import baseball.domain.batter.WieldedBats;
import baseball.domain.pitcher.Balls;

public class Referee {

    private Rule rule;

    private Referee(Rule rule) {
        this.rule = rule;
    }

    public static Referee from(Rule rule) {
        return new Referee(rule);
    }

    public Rule rule() {
        return rule;
    }

    public Score judge(Balls balls, WieldedBats wieldedBats) {
        return null;
    }
}
