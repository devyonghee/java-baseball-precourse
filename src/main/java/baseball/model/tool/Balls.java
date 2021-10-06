package baseball.model.tool;

import java.util.Collection;

import baseball.model.concept.Position;

public final class Balls {

	private final Collection<Ball> ballList;

	private Balls(Collection<Ball> ballList) {
		validate(ballList);
		this.ballList = ballList;
	}

	public static Balls from(Collection<Ball> ballList) {
		return new Balls(ballList);
	}

	/**
	 * <p>공 리스트의 맞은 상태들을 비교하고 가장 우선 순위가 높은 상태를 반환합니다.</p>
	 *
	 * @param hitIndex 공의 index와 비교할 값
	 * @param position 공의 position 과 비교할 값
	 * @return 가장 우선 순위가 높은 상태
	 */
	public HitStatus hit(int hitIndex, Position position) {
		HitStatus status = HitStatus.NOT;
		for (Ball ball : ballList) {
			status = HitStatus.higherPriority(ball.getHitStatus(hitIndex, position), status);
		}
		return status;
	}

	private void validate(Collection<Ball> ballList) {
		if (ballList == null) {
			throw new IllegalArgumentException("ballList must not be null");
		}
	}
}
