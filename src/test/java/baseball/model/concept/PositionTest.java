package baseball.model.concept;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("포지션")
class PositionTest {

    @Test
    @DisplayName("객체화")
    void instance() {
        assertThatNoException().isThrownBy(() -> Position.from(1));
    }

    @ParameterizedTest
    @DisplayName("동일 여부")
    @CsvSource({"0,0,true", "0,1,false"})
    void equals(int positionFirst, int positionSecond, boolean expected) {
        assertThat(Position.from(positionFirst).equals(Position.from(positionSecond)))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("상이 여부")
    @CsvSource({"0,0,false", "0,1,true"})
    void notEquals(int positionFirst, int positionSecond, boolean expected) {
        assertThat(Position.from(positionFirst).notEquals(Position.from(positionSecond)))
                .isEqualTo(expected);
    }
}