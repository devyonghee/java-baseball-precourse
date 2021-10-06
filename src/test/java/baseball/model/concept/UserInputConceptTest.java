package baseball.model.concept;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.mockStatic;

import baseball.BaseBallRule;
import baseball.Range;
import nextstep.utils.Console;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;

@DisplayName("사용자 입력 컨셉")
class UserInputConceptTest {

    @Test
    @DisplayName("객체화")
    void instance() {
        assertThatNoException()
                .isThrownBy(() -> UserInputConcept.from(threeNumberCountAndOneToNineRangeRule()));
    }

    @Test
    @DisplayName("규칙이 없는 객체화")
    void instance_nullRule_illegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> UserInputConcept.from(null))
                .withMessageContaining("rule must not be null");
    }

    @Test
    @DisplayName("사용자 입력 포지션 생성")
    void thinkPositions() {
        try (final MockedStatic<Console> mockConsole = mockStatic(Console.class)) {
            //given
            mockConsole.when(Console::readLine)
                    .thenReturn("123");

            //when, then
            assertThat(UserInputConcept.from(threeNumberCountAndOneToNineRangeRule())
                    .thinkPositions())
                    .hasSize(3)
                    .containsExactly(Position.from(1), Position.from(2), Position.from(3));
        }
    }

    @ParameterizedTest
    @CsvSource({",must be entered numbers", "1,number count", "111,non-duplicate numbers", "012,must be entered", "1 2 3,must be entered only numbers",
            "12345678,number count", "abc,must be entered only numbers", "잘못된값,must be entered only numbers"})
    @DisplayName("규칙에 맞지 않는 사용자 입력 포지션 생성")
    void thinkPositions_invalidInput_illegalArgumentException(String invalidInput, String expectedMessage) {
        try (final MockedStatic<Console> mockConsole = mockStatic(Console.class)) {
            //given
            mockConsole.when(Console::readLine)
                    .thenReturn(invalidInput);

            //when, then
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> UserInputConcept.from(threeNumberCountAndOneToNineRangeRule())
                            .thinkPositions())
                    .withMessageContaining(expectedMessage);
        }
    }

    private BaseBallRule threeNumberCountAndOneToNineRangeRule() {
        return BaseBallRule.of(3, Range.of(1, 9));
    }
}