package baseball.model.tool;

enum HitStatus {
	STRIKE(2), BALL(1), NOT(0);

	private final int priority;

	HitStatus(int priority) {
		this.priority = priority;
	}

	/**
	 * <p>순서 및 포지션 동일 여부를 통해 적합한 상태를 반환합니다.</p>
	 *
	 * @param differentPosition 포지션 상이 여부
	 * @param differentIndex    순서 상이 여부
	 * @return 맞은 공 상태
	 */
	static HitStatus valueOf(boolean differentPosition, boolean differentIndex) {
		if (differentPosition) {
			return HitStatus.NOT;
		}
		if (differentIndex) {
			return HitStatus.BALL;
		}
		return HitStatus.STRIKE;
	}

	/**
	 * <p>두 인자 중 우선순위가 더 높은 상태를 반환합니다.</p>
	 *
	 * @param statusFirst  첫번째 상태
	 * @param statusSecond 두번째 상태
	 * @return 우선순위가 더 높은 상태
	 */
	static HitStatus higherPriority(HitStatus statusFirst, HitStatus statusSecond) {
		if (statusFirst.priority >= statusSecond.priority) {
			return statusFirst;
		}
		return statusSecond;
	}
}
