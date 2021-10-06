package baseball.model;

import java.util.HashSet;

import baseball.model.concept.Concept;
import baseball.model.concept.Position;
import baseball.model.tool.Ball;
import baseball.model.tool.Balls;

public final class Pitcher implements Player {

	private final Concept concept;

	private Pitcher(Concept concept) {
		validate(concept);
		this.concept = concept;
	}

	public static Pitcher from(Concept concept) {
		return new Pitcher(concept);
	}

	/**
	 * <p>concept으로부터 생성된 포지션을 통해 index 를 부여하고 balls 를 생성합니다.</p>
	 *
	 * @return 순서와 포지션을 가진 공들
	 */
	public Balls throwBalls() {
		HashSet<Ball> ballList = new HashSet<>();
		int index = 0;
		for (Position position : concept.thinkPositions()) {
			ballList.add(Ball.of(index++, position));
		}
		return Balls.from(ballList);
	}

	private void validate(Concept concept) {
		if (concept == null) {
			throw new IllegalArgumentException("concept must not be null");
		}
	}
}
