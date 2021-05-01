package racingcar.domain.racingrule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("진조건(0 ~ 9)안에서 랜덤하게 생성되는 지 테스트")
class RandomRacingMoveRuleTest {

    private RandomRacingMoveRule randomRacingMoveRule;

    @BeforeEach
    void init(){
        randomRacingMoveRule = new RandomRacingMoveRule();
    }

    @DisplayName("전진하는 조건이 0에서 9 사이인지 테스트함")
    @RepeatedTest(100)
    void randomRaceRange() {
        int raceConditionRange = randomRacingMoveRule.getRaceConditionRange();
        assertThat(raceConditionRange).isBetween(0, 9);
    }
}