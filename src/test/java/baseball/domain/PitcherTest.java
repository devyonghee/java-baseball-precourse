package baseball.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import baseball.domain.concept.Concept;
import baseball.domain.concept.Position;
import java.util.Arrays;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("투수")
class PitcherTest {

    @Test
    @DisplayName("객체화")
    void instance() {
        assertThatNoException().isThrownBy(() -> Pitcher.from(mock(Concept.class)));
    }

    @Test
    @DisplayName("컨셉이 없는 객체화")
    void instance_nullConcept_IllegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException().isThrownBy(() -> Pitcher.from(null))
                .withMessageContaining("concept must not be null");
    }

    @Test
    @DisplayName("공 던지기")
    void throwBalls() {
        Concept mockConcept = mock(Concept.class);
        when(mockConcept.thinkPositions()).thenReturn(Arrays.asList(Position.from(1), Position.from(2), Position.from(3)));

        assertThat(Pitcher.from(mockConcept).throwBalls())
                .extracting("ballList", InstanceOfAssertFactories.ITERABLE)
                .extracting("index", "position")
                .contains(tuple(0, Position.from(1)), tuple(1, Position.from(2)), tuple(2, Position.from(3)));
    }
}