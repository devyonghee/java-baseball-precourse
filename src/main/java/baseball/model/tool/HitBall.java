package baseball.model.tool;

final class HitBall {

	private final HitStatus status;

	private HitBall(HitStatus status) {
		validate(status);
		this.status = status;
	}

	static HitBall from(HitStatus status) {
		return new HitBall(status);
	}

	/**
	 * <p>맞은 공의 상태를 세기 위해 상태를 반환합니다.</p>
	 *
	 * @return 맞은 상태
	 */
	HitStatus getStatus() {
		return status;
	}

	private void validate(HitStatus status) {
		if (status == null) {
			throw new IllegalArgumentException("status must not be null");
		}
	}
}
