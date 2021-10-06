package baseball.model.tool;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

public final class HitBalls {

	private final Collection<HitBall> hitBallList;
	private final Map<HitStatus, Integer> counts = new EnumMap<>(HitStatus.class);

	private HitBalls(Collection<HitBall> hitBallList) {
		validate(hitBallList);
		this.hitBallList = hitBallList;
	}

	public static HitBalls from(Collection<HitBall> hitBalls) {
		return new HitBalls(hitBalls);
	}

	/**
	 * <p>hitBallList 통해 상태별 갯수를 계산하고 스트라이크 갯수를 반환합니다.</p>
	 *
	 * @return 스트라이크 갯수
	 */
	public int getStrikeCount() {
		checkCount();
		return counts.get(HitStatus.STRIKE);
	}

	/**
	 * <p>hitBallList 통해 상태별 갯수를 계산하고 볼 갯수를 반환합니다.</p>
	 *
	 * @return 볼 갯수
	 */
	public int getBallCount() {
		checkCount();
		return counts.get(HitStatus.BALL);
	}

	private void validate(Collection<HitBall> hitBallList) {
		if (hitBallList == null) {
			throw new IllegalArgumentException("hitBallList must not be null");
		}
	}

	private void checkCount() {
		if (counts.isEmpty()) {
			calculate();
		}
	}

	private void calculate() {
		initCounts();
		countStatuses();
	}

	private void initCounts() {
		for (HitStatus status : HitStatus.values()) {
			counts.putIfAbsent(status, 0);
		}
	}

	private void countStatuses() {
		for (HitBall ball : hitBallList) {
			counts.computeIfPresent(ball.getStatus(), (key, count) -> count + 1);
		}
	}

}
