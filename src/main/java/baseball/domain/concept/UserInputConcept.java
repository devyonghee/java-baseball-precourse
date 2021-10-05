package baseball.domain.concept;

import baseball.BaseBallRule;
import java.util.Collection;
import java.util.LinkedHashSet;
import nextstep.utils.Console;

public final class UserInputConcept implements Concept {

    private final BaseBallRule rule;

    public UserInputConcept(BaseBallRule rule) {
        validate(rule);
        this.rule = rule;
    }

    public static UserInputConcept from(BaseBallRule rule) {
        return new UserInputConcept(rule);
    }

    @Override
    public Collection<Position> thinkPositions() {
        LinkedHashSet<Position> positions = getPositions(getSplitInputs(Console.readLine()));
        validateSize(positions);
        return positions;
    }

    private void validateSize(LinkedHashSet<Position> positions) {
        if (rule.isDifferentNumberCountFrom(positions.size())) {
            throw new IllegalArgumentException(
                    String.format("must enter %d number count, but entered number count is %d", rule.getNumberCount(), positions.size()));
        }
    }

    private LinkedHashSet<Position> getPositions(String[] inputs) {
        LinkedHashSet<Position> positions = new LinkedHashSet<>();
        for (String input : inputs) {
            int number = parseInt(input);
            validateRange(number);
            positions.add(Position.from(number));
        }
        return positions;
    }

    private void validateRange(int number) {
        if (rule.isOutOfRange(number)) {
            throw new IllegalArgumentException(
                    String.format("must be entered %d to %d, but entered %d", rule.getMinRange(), rule.getMaxRange(), number));
        }
    }

    private String[] getSplitInputs(String input) {
        if (input == null || "".equals(input.trim())) {
            throw new IllegalArgumentException("must be entered numbers");
        }
        return input.split("");
    }

    private int parseInt(String s) {
        try {
            return Integer.parseInt(s);
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
