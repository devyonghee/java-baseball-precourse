package baseball.model.concept;

import java.io.PrintStream;
import java.util.Collection;

public final class ConceptGuideDecorator implements Concept {

    private final PrintStream printStream;
    private final String guide;
    private final Concept concept;

    private ConceptGuideDecorator(Concept concept, PrintStream printStream, String guide) {
        validate(printStream);
        validate(guide);
        validate(concept);
        this.printStream = printStream;
        this.guide = guide;
        this.concept = concept;
    }

    public static Concept from(Concept concept, PrintStream printStream, String guide) {
        return new ConceptGuideDecorator(concept, printStream, guide);
    }

    /**
     * <p>포지션을 생성하기전 필요한 문구를 출력합니다.</p>
     *
     * @return {@link Concept#thinkPositions()}
     */
    @Override
    public Collection<Position> thinkPositions() {
        printStream.print(guide);
        return concept.thinkPositions();
    }

    private void validate(String guide) {
        if (guide == null || "".equals(guide.trim())) {
            throw new IllegalArgumentException("guide must be provided");
        }
    }

    private void validate(Concept concept) {
        if (concept == null) {
            throw new IllegalArgumentException("concept must not be null");
        }
    }

    private void validate(PrintStream printStream) {
        if (printStream == null) {
            throw new IllegalArgumentException("printStream must not be null");
        }
    }
}
