package baseball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("범위")
class RangeTest {

    @Test
    @DisplayName("객체화")
    void instance() {
        assertThatNoException().isThrownBy(() -> Range.of(1, 9));
    }

    @Test
    @DisplayName("최소 범위를 벗어난 객체화")
    void instance_outOfMinRange_illegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException().isThrownBy(() -> Range.of(0, 9));
    }

    @Test
    @DisplayName("최대 범위를 벗어난 객체화")
    void instance_outOfMaxRange_illegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException().isThrownBy(() -> Range.of(1, 10));
    }

    @Test
    @DisplayName("최대가 최소보다 작은 객체화")
    void instance_nullRange_illegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException().isThrownBy(() -> Range.of(9, 1));
    }

    @ParameterizedTest
    @DisplayName("미포함 여부")
    @CsvSource({"0,true", "1,false", "5,false", "10,true"})
    void isOut(int number, boolean expected) {
        assertThat(Range.of(1, 9).isOut(number))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("최소 정보")
    void getMin() {
        assertThat(Range.of(1, 9).getMin())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("최대 정보")
    void getMax() {
        assertThat(Range.of(1, 9).getMax())
                .isEqualTo(9);
    }

    @Test
    @DisplayName("크기 정보")
    void size() {
        assertThat(Range.of(1, 9).size())
                .isEqualTo(9);
    }
}