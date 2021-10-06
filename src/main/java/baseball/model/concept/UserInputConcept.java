package baseball.model.concept;

import java.util.Collection;
import java.util.LinkedHashSet;

import baseball.BaseBallRule;
import nextstep.utils.Console;

public final class UserInputConcept implements Concept {

	private final BaseBallRule rule;

	private UserInputConcept(BaseBallRule rule) {
		validate(rule);
		this.rule = rule;
	}

	public static UserInputConcept from(BaseBallRule rule) {
		return new UserInputConcept(rule);
	}

	/**
	 * <p>사용자가 입력한 값을 통해 포지션 콜렉션을 반환한다.</p>
	 *
	 * @return 포지션 리스트
	 * @throws IllegalArgumentException 사용자가 입력한 값이 규칙에 맞지 않는 경우 반환
	 */
	@Override
	public Collection<Position> thinkPositions() {
		LinkedHashSet<Position> positions = getPositions(getSplitInputs(Console.readLine()));
		validateSize(positions);
		return positions;
	}

	private void validateSize(LinkedHashSet<Position> positions) {
		if (rule.isDifferentNumberCountFrom(positions.size())) {
			throw new IllegalArgumentException(
				String.format("must be entered %d number count, but entered number count is %d", rule.getNumberCount(),
					positions.size()));
		}
	}

	private LinkedHashSet<Position> getPositions(String[] inputs) {
		LinkedHashSet<Position> positions = new LinkedHashSet<>();
		for (String input : inputs) {
			int number = parseInt(input);
			validate(positions, number);
			positions.add(Position.from(number));
		}
		return positions;
	}

	private void validate(Collection<Position> positions, int number) {
		validateRange(number);
		validateExists(positions, number);
	}

	private void validateExists(Collection<Position> positions, int number) {
		if (positions.contains(Position.from(number))) {
			throw new IllegalArgumentException(
				String.format("must be entered non-duplicate numbers, %d is already entered", number));
		}
	}

	private void validateRange(int number) {
		if (rule.isOutOfRange(number)) {
			throw new IllegalArgumentException(
				String.format("must be entered %d to %d, but entered %d", rule.getMinRange(), rule.getMaxRange(),
					number));
		}
	}

	private String[] getSplitInputs(String input) {
		if (input == null || "".equals(input.trim())) {
			throw new IllegalArgumentException("must be entered numbers");
		}
		return input.split("");
	}

	private int parseInt(String string) {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException exception) {
			throw new IllegalArgumentException("must be entered only numbers", exception);
		}
	}

	private void validate(BaseBallRule rule) {
		if (rule == null) {
			throw new IllegalArgumentException("rule must not be null");
		}
	}
}
