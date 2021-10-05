package baseball.domain.concept;

import baseball.BaseBallRule;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
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
