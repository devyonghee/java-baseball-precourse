package baseball.model.tool;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import baseball.model.concept.Position;

@DisplayName("배트")
class BatTest {

	@Test
	@DisplayName("객체화")
	void instance() {
		assertThatNoException()
			.isThrownBy(() -> Bat.of(1, Position.from(1)));
	}

	@Test
	@DisplayName("포지션이 없는 객체화")
	void instance_nullPosition_illegalArgumentExceptionThrown() {
		assertThatIllegalArgumentException()
			.isThrownBy(() -> Bat.of(1, null))
			.withMessageContaining("position must not be null");
	}

	@Test
	@DisplayName("맞은 공 상태")
	void hit() {
		//given
		HitStatus notStatus = HitStatus.NOT;
		Balls mockBalls = mock(Balls.class);
		when(mockBalls.hit(anyInt(), any(Position.class))).thenReturn(notStatus);

		//when, then
		assertThat(Bat.of(0, Position.from(1))
			.hit(mockBalls))
			.extracting("status")
			.isEqualTo(notStatus);
	}
}
