package baseball.view;

import baseball.controller.dto.Score;
import java.io.PrintStream;

public final class Billboard implements Display<Score> {

    private static final String NOTHING_SENTENCE = "낫싱";
    private static final String STRIKE_SENTENCE_FORMAT = "%d스트라이크";
    private static final String BALL_SENTENCE_FORMAT = "%d볼";
    private static final String STRIKE_AND_BALL_SENTENCE_FORMAT = String.join(" ", STRIKE_SENTENCE_FORMAT, BALL_SENTENCE_FORMAT);
    private static final String ERROR_SENTENCE = "[ERROR]";

    private final PrintStream display;

    private Billboard(PrintStream display) {
        this.display = display;
    }

    public static Billboard from(PrintStream display) {
        return new Billboard(display);
    }

    @Override
    public void exposure(Score score) {
        if (score.hasError()) {
            this.display.println(ERROR_SENTENCE);
            return;
        }
        if (score.isNothing()) {
            this.display.println(NOTHING_SENTENCE);
            return;
        }
        printScore(score);
        this.display.println();
    }

    private void printScore(Score score) {
        if (score.hasStrikeAndBall()) {
            this.display.printf(STRIKE_AND_BALL_SENTENCE_FORMAT, score.getStrikeCount(), score.getBallCount());
            return;
        }
        if (score.hasStrike()) {
            this.display.printf(STRIKE_SENTENCE_FORMAT, score.getStrikeCount());
            return;
        }
        this.display.printf(BALL_SENTENCE_FORMAT, score.getBallCount());
    }
}
