package baseball.domain.tool;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import baseball.domain.concept.Position;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("공들")
class BallsTest {

    @Test
    @DisplayName("객체화")
    void instance() {
        assertThatNoException()
                .isThrownBy(() -> Balls.from(Collections.singletonList(ball(0, 1))));
    }

    @Test
    @DisplayName("공 리스트가 없는 객체화")
    void instance_nullBallList_illegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Balls.from(null))
                .withMessageContaining("ballList must not be null");
    }

    @ParameterizedTest
    @DisplayName("맞은 상태")
    @MethodSource("providePositionAndExpectedStatus")
    void hit(int index, Position position, HitStatus hitStatus) {
        assertThat(Balls.from(ballList())
                .hit(index, position))
                .isEqualTo(hitStatus);
    }

    private List<Ball> ballList() {
        return Arrays.asList(ball(0, 2), ball(1, 7), ball(2, 3));
    }

    private Ball ball(int index, int position) {
        return Ball.of(index, Position.from(position));
    }

    private static Stream<Arguments> providePositionAndExpectedStatus() {
        return Stream.of(
                Arguments.of(0, Position.from(9), HitStatus.NOT),
                Arguments.of(0, Position.from(7), HitStatus.BALL),
                Arguments.of(2, Position.from(3), HitStatus.STRIKE)
        );
    }
}