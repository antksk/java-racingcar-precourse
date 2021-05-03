package racingcar.domain.core;

import racingcar.domain.RacingCarResult;
import racingcar.domain.racingrule.RacingMoveRule;

import static racingcar.domain.core.RacingCarMovementCount.initMovementCount;
import static racingcar.domain.core.RacingCarName.racingCarName;

class RacingCar {
    private final RacingCarName racingCarName;
    private final RacingCarMovementCount racingCarMovementCount;

    private RacingCar(String name) {
        this.racingCarName = racingCarName(name);
        this.racingCarMovementCount = initMovementCount();
    }

    static RacingCar raceAttend(String name){
        return new RacingCar(name);
    }

    RacingCar racing(RacingMoveRule racingMoveRule){
        if (racingMoveRule.isMove()){
            racingCarMovementCount.moving();
        }
        return this;
    }

    String name(){
        return racingCarName.toName();
    }

    int moveCount(){
        return racingCarMovementCount.toCount();
    }

    RacingCarResult toResult(){
        return new RacingCarResult(name(), moveCount());
    }
}
