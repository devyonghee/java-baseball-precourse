package baseball.model.tool;

enum HitStatus {
    STRIKE(2), BALL(1), NOT(0);

    private final int priority;

    HitStatus(int priority) {
        this.priority = priority;
    }

    static HitStatus valueOf(boolean differentPosition, boolean differentIndex) {
        if (differentPosition) {
            return HitStatus.NOT;
        }
        if (differentIndex) {
            return HitStatus.BALL;
        }
        return HitStatus.STRIKE;
    }

    static HitStatus higherPriority(HitStatus statusFirst, HitStatus statusSecond) {
        if (statusFirst.priority >= statusSecond.priority) {
            return statusFirst;
        }
        return statusSecond;
    }
}
