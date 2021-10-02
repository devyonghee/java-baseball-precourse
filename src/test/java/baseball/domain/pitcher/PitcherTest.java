package baseball.domain.pitcher;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

import baseball.domain.Range;
import baseball.domain.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PitcherTest {

    @Test
    @DisplayName("투수 객체화")
    void instance() {
        assertThatNoException().isThrownBy(() -> Pitcher.from(threeCountAndOneToNineRangeRule()));
    }

    @Test
    @DisplayName("룰이 없는 투수 객체화")
    void instance_nullRule_IllegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException().isThrownBy(() -> Pitcher.from(null))
                .withMessageContaining("rule must not be null");
    }

    @Test
    @DisplayName("공 생성")
    void throwBalls() {
        assertThat(Pitcher.from(threeCountAndOneToNineRangeRule()).throwBalls())
                .extracting(Balls::isFull)
                .isEqualTo(true);
    }

    private Rule threeCountAndOneToNineRangeRule() {
        return Rule.of(3, Range.of(1, 9));
    }
}