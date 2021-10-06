package baseball.model;

import java.util.HashSet;
import java.util.Set;

import baseball.model.concept.Concept;
import baseball.model.concept.Position;
import baseball.model.tool.Bat;
import baseball.model.tool.Bats;

public final class Batter implements Player {

	private final Concept concept;

	private Batter(Concept concept) {
		validate(concept);
		this.concept = concept;
	}

	public static Batter from(Concept concept) {
		return new Batter(concept);
	}

	/**
	 * <p>concept으로부터 생성된 포지션을 통해 index 를 부여하고 bats 를 생성합니다.</p>
	 *
	 * @return 순서와 포지션을 가진 배트들
	 */
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
