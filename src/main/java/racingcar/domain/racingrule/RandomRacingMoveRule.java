package racingcar.domain.racingrule;

import java.util.Random;

final class RandomRacingMoveRule implements RacingMoveRule {
    public static final int RACE_CONDITION = 4;
    public static final int RACE_MOVE_BOUND = 10;
    private final Random random;

    RandomRacingMoveRule() {
        random = new Random();
    }

    @Override
    public boolean isMove() {
        return RACE_CONDITION >= getRaceConditionRange();
    }

    int getRaceConditionRange() {
        return random.nextInt(RACE_MOVE_BOUND);
    }
}
