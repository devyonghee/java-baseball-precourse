package baseball.view;

public interface Display<T> {

    void exposure(T target);

    void printError();

}