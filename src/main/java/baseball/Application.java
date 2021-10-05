package baseball;

import baseball.controller.BaseballStadium;
import baseball.controller.Stadium;
import baseball.view.Billboard;

public class Application {

    private static final BaseBallRule BASEBALL_GAME_RULE = BaseBallRule.of(3, Range.of(1, 9));

    public static void main(String[] args) {
        Moderator moderator = Moderator.of(System.out, BASEBALL_GAME_RULE.getNumberCount());
        Stadium stadium = BaseballStadium.of(BASEBALL_GAME_RULE, Billboard.from(System.out));
        do {
            stadium.playBall();
            moderator.guideEndGame();
        } while (moderator.continueGame());
    }
}
