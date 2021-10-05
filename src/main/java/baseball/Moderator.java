package baseball;

import java.io.PrintStream;
import java.util.Arrays;
import nextstep.utils.Console;

public final class Moderator {

    private static final String END_GAME_SENTENCE_FORMAT = "%d개의 숫자를 모두 맞히셨습니다! 게임 끝";
    private static final String INVALID_INPUT_SENTENCE = "잘못된 입력입니다. 다시 입력해주세요.";
    private static final String CONTINUE_GAME_FLAG = "1";
    private static final String END_GAME_FLAG = "2";
    private static final String CONTINUE_ASK_GAME_SENTENCE = String.format("게임을 새로 시작하려면 %s, 종료하려면 %s를 입력하세요.", CONTINUE_GAME_FLAG, END_GAME_FLAG);
    private final PrintStream printStream;
    private final int numberCount;

    private Moderator(PrintStream printStream, int numberCount) {
        validate(printStream);
        this.printStream = printStream;
        this.numberCount = numberCount;
    }

    public static Moderator of(PrintStream printStream, int numberCount) {
        return new Moderator(printStream, numberCount);
    }

    public boolean continueGame() {
        printStream.println(CONTINUE_ASK_GAME_SENTENCE);
        String input = Console.readLine();
        if (isInvalidInput(input)) {
            return continueGame();
        }
        return CONTINUE_GAME_FLAG.equals(input);
    }

    public void printEndGame() {
        printStream.printf(END_GAME_SENTENCE_FORMAT, numberCount);
        printStream.println();
    }

    private void validate(PrintStream printStream) {
        if (printStream == null) {
            throw new IllegalArgumentException("printStream must not be null");
        }
    }

    private boolean isInvalidInput(String readLine) {
        if (Arrays.asList(CONTINUE_GAME_FLAG, END_GAME_FLAG).contains(readLine)) {
            return false;
        }
        printStream.println(INVALID_INPUT_SENTENCE);
        return true;
    }
}
