package baseball.model.tool;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("맞은 공들")
class HitBallsTest {

	private static Stream<Arguments> provideHitBallListAndExpectedBallCount() {
		return Stream.of(
			Arguments.of(
				Arrays.asList(HitBall.from(HitStatus.NOT), HitBall.from(HitStatus.NOT), HitBall.from(HitStatus.STRIKE)),
				0),
			Arguments.of(Arrays.asList(HitBall.from(HitStatus.STRIKE), HitBall.from(HitStatus.STRIKE),
				HitBall.from(HitStatus.BALL)), 1),
			Arguments.of(
				Arrays.asList(HitBall.from(HitStatus.BALL), HitBall.from(HitStatus.BALL), HitBall.from(HitStatus.BALL)),
				3)
		);
	}

	private static Stream<Arguments> provideHitBallListAndExpectedStrikeCount() {
		return Stream.of(
			Arguments.of(
				Arrays.asList(HitBall.from(HitStatus.BALL), HitBall.from(HitStatus.BALL), HitBall.from(HitStatus.BALL)),
				0),
			Arguments.of(
				Arrays.asList(HitBall.from(HitStatus.NOT), HitBall.from(HitStatus.NOT), HitBall.from(HitStatus.STRIKE)),
				1),
			Arguments.of(Arrays.asList(HitBall.from(HitStatus.STRIKE), HitBall.from(HitStatus.STRIKE),
				HitBall.from(HitStatus.BALL)), 2)
		);
	}

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException().isThrownBy(
			() -> HitBalls.from(Collections.singletonList(HitBall.from(HitStatus.STRIKE))));
	}

	@Test
	@DisplayName("맞은 공 리스트가 없는 객체화")
	void instance_nullHitBallList_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> HitBalls.from(null))
			.withMessageContaining("hitBallList must not be null");
	}

	@ParameterizedTest
	@MethodSource("provideHitBallListAndExpectedBallCount")
	@DisplayName("볼 갯수 정보")
	void getBallCount(Collection<HitBall> hitBallList, int ballCount) {
		assertThat(HitBalls.from(hitBallList)
			.getBallCount())
			.isEqualTo(ballCount);
	}

	@ParameterizedTest
	@MethodSource("provideHitBallListAndExpectedStrikeCount")
	@DisplayName("볼 갯수 정보")
	void getStrikeCount(Collection<HitBall> hitBallList, int strikeCount) {
		assertThat(HitBalls.from(hitBallList)
			.getStrikeCount())
			.isEqualTo(strikeCount);
	}

}
