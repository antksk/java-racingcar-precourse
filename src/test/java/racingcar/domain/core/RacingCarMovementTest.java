package racingcar.domain.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static racingcar.domain.core.RacingCarMovement.MAX_MOVE_COUNT;

@DisplayName("레이싱카 이동 테스트")
class RacingCarMovementTest {

    private void repeat(final int count, Supplier<RacingCarMovement> moving){
        for(int i =0; count > i; ++i){
            moving.get();
        }
    }

    @DisplayName("moving 메소드를 호출한 만큼 이동 횟수를 확인 함")
    @Test
    void moving() {
        RacingCarMovement racingCarMovement = RacingCarMovement.initMovement();

        assertThat(racingCarMovement.getMovingCount()).isNotEqualTo(-1);

        assertThat(racingCarMovement.moving().getMovingCount()).isEqualTo(1);

        repeat(3, racingCarMovement::moving);
        assertThat(racingCarMovement.getMovingCount()).isEqualTo(4);

        repeat(6, racingCarMovement::moving);
        assertThat(racingCarMovement.getMovingCount()).isEqualTo(10);


        repeat(100, racingCarMovement::moving);
        assertThat(racingCarMovement.getMovingCount()).isEqualTo(MAX_MOVE_COUNT);
    }

    @DisplayName("toRender 메소드를 호출하여 지정된 문자열로 이동한 회수를 표현")
    @Test
    void rendering(){
        RacingCarMovement racingCarMovement = RacingCarMovement.initMovement();
        final String renderStyle = "-";
        assertThat(racingCarMovement.toRender(renderStyle)).isEqualTo("");

        repeat(3, racingCarMovement::moving);
        assertThat(racingCarMovement.toRender(renderStyle)).isEqualTo("---");

        repeat(100, racingCarMovement::moving);
        assertThat(racingCarMovement.toRender(renderStyle)).isEqualTo("----------");
    }

}
