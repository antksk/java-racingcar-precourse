package racingcar.domain.core;

final class RacingCarMovementCount {
    static final int MAX_MOVE_COUNT = 10;
    private int movementCount;

    RacingCarMovementCount() {
        this.movementCount = 0;
    }

    static RacingCarMovementCount initMovement() {
        return new RacingCarMovementCount();
    }

    RacingCarMovementCount moving() {
        if (MAX_MOVE_COUNT > movementCount) {
            movementCount++;
        }
        return this;
    }

    int toCount() {
        return movementCount;
    }
}
