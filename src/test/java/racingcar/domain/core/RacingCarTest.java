package racingcar.domain.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.domain.racingrule.RacingMoveRule;

import static org.assertj.core.api.Assertions.assertThat;
import static racingcar.domain.core.RacingCar.raceAttend;

@DisplayName("레이싱카가 정상적으로 움직이니는 테스트")
class RacingCarTest {

    @DisplayName("레이싱 자동차를 전진 시킴")
    @Test
    void go() {
        RacingCar car = raceAttend("going");
        RacingMoveRule goRule = () -> true;
        assertThat(car.racing(goRule).toResult().getMovementCount()).isPositive();
    }

    @DisplayName("레이싱 자동차를 정지 시킴")
    @Test
    void stop() {
        RacingCar car = raceAttend("stop");
        RacingMoveRule stopRule = () -> false;
        assertThat(car.racing(stopRule).toResult().getMovementCount()).isZero();
    }

}