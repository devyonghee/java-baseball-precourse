package baseball.model.tool;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import baseball.model.concept.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("공")
class BallTest {

    @Test
    @DisplayName("객체화")
    void instance() {
        assertThatNoException()
                .isThrownBy(() -> Ball.of(1, Position.from(1)));
    }

    @Test
    @DisplayName("포지션이 없는 객체화")
    void instance_nullPosition_illegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Ball.of(1, null))
                .withMessageContaining("position must not be null");
    }

    @ParameterizedTest
    @DisplayName("맞은 상태")
    @MethodSource("providePositionAndExpectedStatus")
    void getHitStatus(int index, Position position, HitStatus expectedStatus) {
        assertThat(Ball.of(1, Position.from(1))
                .getHitStatus(index, position))
                .isEqualTo(expectedStatus);
    }

    private static Stream<Arguments> providePositionAndExpectedStatus() {
        return Stream.of(
                Arguments.of(1, Position.from(2), HitStatus.NOT),
                Arguments.of(2, Position.from(1), HitStatus.BALL),
                Arguments.of(1, Position.from(1), HitStatus.STRIKE)
        );
    }
}