package baseball.model.concept;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("컨셉 설명 데코레이터")
class ConceptGuideDecoratorTest {

    @Test
    @DisplayName("객체화")
    void instance() {
        assertThatNoException()
                .isThrownBy(() -> ConceptGuideDecorator.from(mock(Concept.class), mock(PrintStream.class), "any"));
    }

    @Test
    @DisplayName("컨셉이 없는 객체화")
    void instance_nullConcept_illegalArgumentExceptionThrown() {
            assertThatIllegalArgumentException()
                .isThrownBy(() -> ConceptGuideDecorator.from(null, mock(PrintStream.class), "any"))
                .withMessageContaining("concept must not be null");
    }

    @Test
    @DisplayName("프린트 도구가 없는 객체화")
    void instance_nullPrinter_illegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ConceptGuideDecorator.from(mock(Concept.class), null, "any"))
                        .withMessageContaining("printStream must not be null");

        }

    @Test
    @DisplayName("가이드 문구가 없는 객체화")
    void instance_emptyGuide_illegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> ConceptGuideDecorator.from(mock(Concept.class), mock(PrintStream.class), ""))
                        .withMessageContaining("guide must be provided");
        }

    @Test
    @DisplayName("가이드 문구 출력")
    void thinkPositions() {
        //given
        PrintStream printStream = mock(PrintStream.class);
        Concept concept = mock(Concept.class);
        String guideString = "guide";

        //when
        ConceptGuideDecorator.from(concept, printStream, guideString).thinkPositions();

        //then
        verify(printStream, only()).print(guideString);
        verify(concept, only()).thinkPositions();
    }
}