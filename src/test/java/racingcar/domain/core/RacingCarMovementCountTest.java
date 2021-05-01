package racingcar.domain.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static racingcar.domain.core.RacingCarMovementCount.MAX_MOVE_COUNT;

@DisplayName("레이싱카 이동 테스트")
class RacingCarMovementCountTest {

    private void repeat(final int count, Supplier<RacingCarMovementCount> moving){
        for(int i =0; count > i; ++i){
            moving.get();
        }
    }

    @DisplayName("moving 메소드를 호출한 만큼 이동 횟수를 확인 함")
    @Test
    void moving() {
        RacingCarMovementCount racingCarMovementCount = RacingCarMovementCount.initMovement();

        assertThat(racingCarMovementCount.toCount()).isNotEqualTo(-1);

        assertThat(racingCarMovementCount.moving().toCount()).isEqualTo(1);

        repeat(3, racingCarMovementCount::moving);
        assertThat(racingCarMovementCount.toCount()).isEqualTo(4);

        repeat(6, racingCarMovementCount::moving);
        assertThat(racingCarMovementCount.toCount()).isEqualTo(10);


        repeat(100, racingCarMovementCount::moving);
        assertThat(racingCarMovementCount.toCount()).isEqualTo(MAX_MOVE_COUNT);
    }

    String toRender(int movingCount, String renderStyle){
        StringBuilder sb = new StringBuilder(movingCount);
        for (int i = 0; movingCount > i; ++i) {
            sb.append(renderStyle);
        }
        return sb.toString();
    }
    @DisplayName("toRender 메소드를 호출하여 지정된 문자열로 이동한 회수를 표현")
    @Test
    void rendering(){
        RacingCarMovementCount racingCarMovementCount = RacingCarMovementCount.initMovement();
        final String renderStyle = "-";
        assertThat(toRender(racingCarMovementCount.toCount(), renderStyle)).isEqualTo("");

        repeat(3, racingCarMovementCount::moving);
        assertThat(toRender(racingCarMovementCount.toCount(), renderStyle)).isEqualTo("---");

        repeat(100, racingCarMovementCount::moving);
        assertThat(toRender(racingCarMovementCount.toCount(), renderStyle)).isEqualTo("----------");
    }

}
