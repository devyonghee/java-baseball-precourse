package baseball.domain.tool;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("맞은 상태")
class HitStatusTest {

    @ParameterizedTest
    @DisplayName("옵션에 따른 상태")
    @CsvSource({"true,true,NOT", "true,false,NOT", "false,true,BALL", "false,false,STRIKE"})
    void valueOf(boolean differentPosition, boolean differentIndex, HitStatus expectedStatus) {
        assertThat(HitStatus.valueOf(differentPosition, differentIndex)).isEqualTo(expectedStatus);
    }

    @ParameterizedTest
    @DisplayName("우선순위가 더 높은 상태")
    @CsvSource({"NOT,NOT,NOT", "NOT,BALL,BALL", "BALL,NOT,BALL", "STRIKE,NOT,STRIKE", "NOT,STRIKE,STRIKE", "BALL,STRIKE,STRIKE"})
    void higherPriority(HitStatus statusFirst, HitStatus statusSecond, HitStatus expectedStatus) {
        assertThat(HitStatus.higherPriority(statusFirst, statusSecond)).isEqualTo(expectedStatus);
    }
}