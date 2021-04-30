package racingcar.domain.core;

final class RacingCarMovement {
    static final int MAX_MOVE_COUNT = 10;
    private int move;

    RacingCarMovement() {
        this.move = 0;
    }

    static RacingCarMovement initMovement() {
        return new RacingCarMovement();
    }

    RacingCarMovement moving() {
        if (MAX_MOVE_COUNT > move) {
            move++;
        }
        return this;
    }

    int getMovingCount() {
        return move;
    }

    String toRender(final String renderStyle) {
        StringBuilder sb = new StringBuilder(MAX_MOVE_COUNT);
        for (int i = 0; move > i; ++i) {
            sb.append(renderStyle);
        }
        return sb.toString();
    }
}
