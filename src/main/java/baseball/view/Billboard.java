package baseball.view;

import java.io.PrintStream;

import baseball.controller.dto.Score;

public final class Billboard implements Display<Score> {

	private static final String NOTHING_SENTENCE = "낫싱";
	private static final String STRIKE_SENTENCE_FORMAT = "%d스트라이크";
	private static final String BALL_SENTENCE_FORMAT = "%d볼";
	private static final String STRIKE_AND_BALL_SENTENCE_FORMAT = String.join(" ", STRIKE_SENTENCE_FORMAT,
		BALL_SENTENCE_FORMAT);
	private static final String ERROR_SENTENCE_FORMAT = "[ERROR] %s";

	private final PrintStream display;

	private Billboard(PrintStream display) {
		this.display = display;
	}

	public static Billboard from(PrintStream display) {
		return new Billboard(display);
	}

	/**
	 * <p>스코어의 정보를 출력합니다.</p>
	 *
	 * @param score 출력할 점수
	 */
	@Override
	public void exposure(Score score) {
		if (score.isNothing()) {
			this.display.println(NOTHING_SENTENCE);
			return;
		}
		printScore(score);
		this.display.println();
	}

	@Override
	public void printError(String message) {
		this.display.printf(ERROR_SENTENCE_FORMAT, message);
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
