package baseball.model;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import baseball.model.concept.Concept;
import baseball.model.concept.Position;
import java.util.Arrays;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("타자")
class BatterTest {

    @Test
    @DisplayName("객체화")
    void instance() {
        assertThatNoException().isThrownBy(() -> Batter.from(mock(Concept.class)));
    }

    @Test
    @DisplayName("컨셉이 없는 객체화")
    void instance_nullConcept_IllegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException().isThrownBy(() -> Batter.from(null))
                .withMessageContaining("concept must not be null");
    }

    @Test
    @DisplayName("배트 휘두르기")
    void wieldBats() {
        Concept mockConcept = mock(Concept.class);
        when(mockConcept.thinkPositions()).thenReturn(Arrays.asList(Position.from(1), Position.from(2), Position.from(3)));

        assertThat(Batter.from(mockConcept).wieldBats())
                .extracting("batList", InstanceOfAssertFactories.ITERABLE)
                .extracting("index", "position")
                .contains(tuple(0, Position.from(1)), tuple(1, Position.from(2)), tuple(2, Position.from(3)));
    }
}