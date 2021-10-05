package baseball.domain.tool;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import baseball.domain.concept.Position;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("배트들")
class BatsTest {

    @Test
    @DisplayName("객체화")
    void instance() {
        assertThatNoException()
                .isThrownBy(() -> Bats.from(Collections.singletonList(bat(0, 1))));
    }

    @Test
    @DisplayName("배트 리스트가 없는 객체화")
    void instance_nullBatList_illegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Bats.from(null))
                .withMessageContaining("batList must not be null");
    }

    @Test
    @DisplayName("맞은 공 상태")
    void hit() {
        //given
        Balls mockBalls = mock(Balls.class);
        when(mockBalls.hit(anyInt(), any(Position.class))).thenReturn(HitStatus.STRIKE, HitStatus.NOT, HitStatus.BALL);

        //when, then
        assertThat(Bats.from(Arrays.asList(bat(0, 1), bat(1, 2), bat(2, 2)))
                .hit(mockBalls))
                .extracting(HitBalls::getBallCount, HitBalls::getStrikeCount)
                .containsExactly(1, 1);
    }

    private Bat bat(int index, int position) {
        return Bat.of(index, Position.from(position));
    }
}