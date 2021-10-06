package baseball;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockStatic;

import nextstep.test.NSTest;
import nextstep.utils.Randoms;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class ApplicationTest extends NSTest {
    @BeforeEach
    void beforeEach() {
        super.setUp();
    }

    @Test
    void 낫싱() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms
                    .when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                    .thenReturn(1, 3, 5);
            running("246");
            verify("낫싱");
        }
    }

    @Test
    void 게임종료_후_재시작() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                    .thenReturn(7, 1, 3)
                    .thenReturn(5, 8, 9);
            run("713", "1", "597", "589", "2");
            verify("3스트라이크", "게임 끝", "1스트라이크 1볼");
        }
    }

    @Test
    void 잘못된_입력하면_메세지_출력_후_게임_진행() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                    .thenReturn(2, 1, 9);
            run("잘못된입력", "131", "9", "12345", "", "219", "2");
            verify("[ERROR] must be entered only numbers",
                    "[ERROR] must be entered non-duplicate numbers",
                    "[ERROR] must be entered 3 number count",
                    "[ERROR] must be entered numbers",
                    "게임 끝");
        }
    }

    @Test
    void 랜덤한_중복_숫자_발생해도_게임_진행() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                    .thenReturn(2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 5);
            run("678", "567", "123", "985", "385", "235", "2");
            verify("낫싱", "1볼", "2볼", "1스트라이크", "1스트라이크 1볼", "게임 끝");
        }
    }

    @Test
    void 게임_종료_후_잘못된_입력하면_다시_물어보기() {
        try (final MockedStatic<Randoms> mockRandoms = mockStatic(Randoms.class)) {
            mockRandoms.when(() -> Randoms.pickNumberInRange(anyInt(), anyInt()))
                    .thenReturn(7, 1, 3);
            run("713", "3", "잘못된 입력", "2");
            verify("게임 끝", "[ERROR] 잘못된 입력", "게임을 새로 시작하려면");
        }
    }

    @AfterEach
    void tearDown() {
        outputStandard();
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
