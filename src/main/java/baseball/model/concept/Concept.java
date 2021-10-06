package baseball.model.concept;

import java.util.Collection;

public interface Concept {

	/**
	 * <p>각 컨셉에 맞는 포지션 콜렉션을 반환한다.</p>
	 *
	 * @return 포지션 리스트
	 * @throws IllegalArgumentException 규칙에 올바르지 않는 포지션을 생성할 경우 반환
	 */
	Collection<Position> thinkPositions();
}
