package baseball.model.concept;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;

import baseball.BaseBallRule;
import baseball.Range;
import nextstep.utils.Randoms;

@DisplayName("랜덤 컨셉")
class RandomConceptTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> RandomConcept.from(mock(BaseBallRule.class)));
	}

	@Test
	@DisplayName("규칙이 없는 객체화")
	void instance_nullRule_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> RandomConcept.from(null))
			.withMessageContaining("rule must not be null");
	}

	@ParameterizedTest
	@CsvSource({"1,1,9", "3,4,9", "5,2,8"})
	@DisplayName("규칙의 숫자 갯수 및 범위에 맞는 포지션 생성")
	void thinkPositions(int numberCount, int minRange, int maxRange) {
		assertThat(RandomConcept.from(BaseBallRule.of(numberCount, Range.of(minRange, maxRange))).thinkPositions())
			.hasSize(numberCount)
			.extracting("value", Integer.class)
			.allSatisfy(position -> assertThat(position)
				.isBetween(minRange, maxRange))
			.doesNotHaveDuplicates();
	}

	@Test
	@DisplayName("같은 숫자가 입력되어도 중복되지 않는 포지션 생성")
	void thinkPositions_duplicateNumber() {
		try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
			//given
			mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
				.thenReturn(1, 1, 1, 1, 3, 3, 3, 5);

			//when, then
			assertThat(RandomConcept.from(threeNumberCountAndOneToNineRangeRule())
				.thinkPositions())
				.hasSize(3)
				.extracting("value", Integer.class)
				.allSatisfy(position -> assertThat(position)
					.isBetween(1, 9))
				.doesNotHaveDuplicates();
		}
	}

	private BaseBallRule threeNumberCountAndOneToNineRangeRule() {
		return BaseBallRule.of(3, Range.of(1, 9));
	}
}
