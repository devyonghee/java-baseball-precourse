package baseball.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;

import baseball.BaseBallRule;
import baseball.Range;
import baseball.controller.dto.Score;
import baseball.view.Display;
import nextstep.utils.Console;
import nextstep.utils.Randoms;

@DisplayName("야구 경기장")
class BaseballStadiumTest {

	Display<Score> mockDisplay;
	MockedStatic<Randoms> mockRandoms;
	MockedStatic<Console> mockConsole;

	@BeforeEach
	@SuppressWarnings("unchecked")
	void setup() {
		mockDisplay = mock(Display.class);
		mockRandoms = mockStatic(Randoms.class);
		mockConsole = mockStatic(Console.class);
	}

	@Test
	@DisplayName("1~9까지 3개 숫자 맞추기")
	void playBall_threeCountAndOneToNineRange() {
		//given
		mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt())).
			thenReturn(1, 2, 3);
		mockConsole.when(Console::readLine).
			thenReturn("111", "753", "137", "1", "123");

		//when
		BaseballStadium.of(baseBallRule(3, 1, 9), mockDisplay).playBall();

		//then
		ArgumentCaptor<Score> scoreCaptor = ArgumentCaptor.forClass(Score.class);
		verify(mockDisplay, times(2)).printError(contains("must be entered"));
		verify(mockDisplay, times(3)).exposure(scoreCaptor.capture());
		assertThat(scoreCaptor.getAllValues())
			.extracting(Score::getBallCount, Score::getStrikeCount, Score::hasStrikeAndBall)
			.containsExactly(tuple(0, 1, false), tuple(1, 1, true), tuple(0, 3, false));
	}

	@Test
	@DisplayName("3~8까지 4개 숫자 맞추기")
	void playBall_fourCountAndThreeToEightRange() {
		//given
		mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt())).
			thenReturn(4, 8, 5, 7);
		mockConsole.when(Console::readLine).
			thenReturn("3546", "56", "1529", "4568", "4857");

		//when
		BaseballStadium.of(baseBallRule(4, 2, 9), mockDisplay).playBall();

		//then
		ArgumentCaptor<Score> scoreCaptor = ArgumentCaptor.forClass(Score.class);
		verify(mockDisplay, times(2)).printError(contains("must be entered"));
		verify(mockDisplay, times(3)).exposure(scoreCaptor.capture());
		assertThat(scoreCaptor.getAllValues())
			.extracting(Score::getBallCount, Score::getStrikeCount, Score::hasStrikeAndBall)
			.containsExactly(tuple(2, 0, false), tuple(2, 1, true), tuple(0, 4, false));
	}

	private BaseBallRule baseBallRule(int numberCount, int min, int max) {
		return BaseBallRule.of(numberCount, Range.of(min, max));
	}

	@AfterEach
	void tearDown() {
		mockRandoms.close();
		mockConsole.close();
	}
}
