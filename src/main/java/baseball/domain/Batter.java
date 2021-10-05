package baseball.domain;

import baseball.domain.concept.Concept;
import baseball.domain.concept.Position;
import baseball.domain.tool.Bat;
import baseball.domain.tool.Bats;
import java.util.HashSet;
import java.util.Set;

public final class Batter implements Player {

    private final Concept concept;

    private Batter(Concept concept) {
        validate(concept);
        this.concept = concept;
    }

    public static Batter from(Concept concept) {
        return new Batter(concept);
    }

    public Bats wieldBats() {
        int index = 0;
        Set<Bat> batList = new HashSet<>();
        for (Position position : concept.thinkPositions()) {
            batList.add(Bat.of(index++, position));
        }
        return Bats.from(batList);
    }

    private void validate(Concept concept) {
        if (concept == null) {
            throw new IllegalArgumentException("concept must not be null");
        }
    }
}
