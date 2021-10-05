package baseball.domain.tool;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

@DisplayName("맞은 공")
class HitBallTest {

    @Test
    @DisplayName("객체화")
    void instance() {
        assertThatNoException().isThrownBy(() -> HitBall.from(HitStatus.NOT));
    }

    @Test
    @DisplayName("맞은 상태가 없는 객체화")
    void instance_nullHitStatus_illegalArgumentExceptionThrown() {
        assertThatIllegalArgumentException().isThrownBy(() -> HitBall.from(null));
    }

    @ParameterizedTest
    @EnumSource(HitStatus.class)
    @DisplayName("맞은 상태 정보")
    void getStatus(HitStatus status) {
        assertThat(HitBall.from(status).getStatus()).isEqualTo(status);
    }
}