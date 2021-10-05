package baseball.domain.tool;

final class HitBall {

    private final HitStatus status;

    private HitBall(HitStatus status) {
        validate(status);
        this.status = status;
    }

    static HitBall from(HitStatus status) {
        return new HitBall(status);
    }

    HitStatus getStatus() {
        return status;
    }

    private void validate(HitStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("status must not be null");
        }
    }
}