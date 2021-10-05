package baseball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("야구 게임 규칙")
class BaseBallRuleTest {

    @Test
    @DisplayName("객체화")
    void instance() {
        assertThatNoException()
                .isThrownBy(() -> BaseBallRule.of(3, Range.of(1, 9)));
    }

    @Test
    @DisplayName("숫자 갯수가 음수")
    void instance_negativeNumberCount_illegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> BaseBallRule.of(-1, Range.of(1, 9)))
                .withMessageContaining("numberCount must be greater than 0");
    }

    @Test
    @DisplayName("범위가 없는 객체화")
    void instance_nullRange_illegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> BaseBallRule.of(3, null))
                .withMessageContaining("range must not be null");
    }

    @Test
    @DisplayName("숫자 갯수보다 범위가 작은 객체화")
    void instance_lessThanRangeSize_illegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> BaseBallRule.of(5, Range.of(1, 3)))
                .withMessageContaining("too small than numberCount");
    }

    @Test
    @DisplayName("숫자 갯수 정보")
    void getNumberCount() {
        assertThat(threeCountAndOneToNineRange().getNumberCount())
                .isEqualTo(3);
    }

    @ParameterizedTest
    @CsvSource({"3,false", "10,true"})
    @DisplayName("숫자 갯수 상이 여부")
    void isDifferentNumberCountFrom(int number, boolean expected) {
        assertThat(threeCountAndOneToNineRange().isDifferentNumberCountFrom(number))
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"0,true", "1,false", "5,false", "10,true"})
    @DisplayName("범위 미포함 여부")
    void isOutOfRange(int number, boolean expected) {
        assertThat(threeCountAndOneToNineRange().isOutOfRange(number))
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("최소 범위 정보")
    void getMinRange() {
        assertThat(threeCountAndOneToNineRange().getMinRange())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("최대 범위 정보")
    void getMaxRange() {
        assertThat(threeCountAndOneToNineRange().getMaxRange())
                .isEqualTo(9);
    }

    private BaseBallRule threeCountAndOneToNineRange() {
        return BaseBallRule.of(3, Range.of(1, 9));
    }
}