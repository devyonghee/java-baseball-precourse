package baseball.model.concept;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import baseball.BaseBallRule;
import nextstep.utils.Randoms;

public final class RandomConcept implements Concept {

	private final BaseBallRule rule;

	private RandomConcept(BaseBallRule rule) {
		validate(rule);
		this.rule = rule;
	}

	public static RandomConcept from(BaseBallRule rule) {
		return new RandomConcept(rule);
	}

	/**
	 * <p>랜덤하게 생성된 포지션 콜렉션을 반환한다.</p>
	 * <p>중복 되지 않은 값이 필요한 갯수만큼 만들어질때까지 생성된다.</p>
	 *
	 * @return 포지션 리스트
	 */
	@Override
	public Collection<Position> thinkPositions() {
		Set<Position> positions = new LinkedHashSet<>();
		do {
			positions.add(Position.from(pickNumber()));
		} while (positions.size() < rule.getNumberCount());
		return positions;
	}

	private void validate(BaseBallRule rule) {
		if (rule == null) {
			throw new IllegalArgumentException("rule must not be null");
		}
	}

	private int pickNumber() {
		return Randoms.pickNumberInRange(rule.getMinRange(), rule.getMaxRange());
	}
}
