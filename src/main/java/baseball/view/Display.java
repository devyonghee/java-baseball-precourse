package baseball.view;

public interface Display<T> {

    /**
     * <p>인자로 받은 대상의 정보를 출력합니다.</p>
     *
     * @param target 출력할 대상
     */
    void exposure(T target);

    /**
     * <p>에러가 발생했을 경우 에러 문구를 출력합니다.</p>
     */
    void printError();

}