package racingcar.domain.core;

import racingcar.domain.RacingResult;
import racingcar.domain.racingrule.RacingMoveRule;

import static racingcar.domain.core.RacingCarMovement.initMovement;
import static racingcar.domain.core.RacingCarName.racingCarName;

class RacingCar {
    private final RacingCarName racingCarName;
    private final RacingCarMovement racingCarMovement;

    private RacingCar(String name) {
        this.racingCarName = racingCarName(name);
        this.racingCarMovement = initMovement();
    }

    static RacingCar raceAttend(String name){
        return new RacingCar(name);
    }

    RacingCar racing(RacingMoveRule racingMoveRule){
        if (racingMoveRule.isMove()){
            racingCarMovement.moving();
        }
        return this;
    }

    String name(){
        return racingCarName.toString();
    }

    int totalMovingCount(){
        return racingCarMovement.getMovingCount();
    }

}
