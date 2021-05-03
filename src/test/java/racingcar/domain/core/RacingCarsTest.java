package racingcar.domain.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import racingcar.domain.RacingCarResults;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static racingcar.domain.core.FakeRaceMap.GO_RACE;
import static racingcar.domain.core.RacingCars.*;

@DisplayName("여러대의 레이싱카가 경주 진행 테스트")
class RacingCarsTest {

    @DisplayName("레이싱카들을 등록할때 null이나 공백은 등록할 수없음")
    @Test
    void nullOrEmpty() {
        assertThatIllegalArgumentException()
                .isThrownBy(()-> motorRacing(null))
                .withMessage(ERROR_MSG_EMPTY_CAR_NAMES);

        assertThatIllegalArgumentException()
                .isThrownBy(()-> motorRacing(emptyList()))
                .withMessage(ERROR_MSG_EMPTY_CAR_NAMES);
    }

    @DisplayName("레이싱카들을 등록할때 중복된 이름을 가진 자동차는 등록할수 없")
    @Test
    void duplicate() {
        assertThatIllegalArgumentException()
                .isThrownBy(()-> motorRacing(asList("a", "b", "c", "a")))
                .withMessage(ERROR_MSG_DUPLICATE_CAR_NAMES);

    }

    @DisplayName("이름을 가진 차량별로 모두 전진했을 때, 예상 처리결과 확인")
    @ParameterizedTest
    @CsvSource(value= {
            "a|b|c," +
            "a : -\\n" +
            "b : -\\n" +
            "c : -"
    })
    void namedRacingCarRaceResult(String cars, String expected){
        List<String> names = Arrays.asList(cars.split("\\|"));

        RacingCarResults results = motorRacing(names, () -> true).allRacingCarRace();
        assertThat(results.toString().replace("\n", "\\n")).contains(expected);
    }

    @DisplayName("이름을 가진 차량별로 모두 전진했을 때, 지정된 이동횟수 별로 예상 처리결과 확인")
    @ParameterizedTest
    @CsvSource(value={
        "a|b|c,1," +
            "a : -\\n" +
            "b : -\\n" +
            "c : -"
        , "aa|bb|cc,2," +
            "aa : -\\n" +
            "bb : -\\n" +
            "cc : -\\n\\n" +
            "aa : --\\n" +
            "bb : --\\n" +
            "cc : --"
        , "a|b|c,3," +
            "a : -\\n" +
            "b : -\\n" +
            "c : -\\n\\n" +
            "a : --\\n" +
            "b : --\\n" +
            "c : --\\n\\n" +
            "a : ---\\n" +
            "b : ---\\n" +
            "c : ---"
    })
    void inputWithCarNames(String cars, int raceRound, String expected) {
        List<String> names = Arrays.asList(cars.split("\\|"));

        String result = motorRacing(names, GO_RACE)
                .roundByRacing(raceRound)
                .toString();

        assertThat(result.replace("\n", "\\n")).contains(expected);
    }

    @DisplayName("경주 진행이 끝나고 모든 레이싱카를 조사해서, 최대 이동 횟수함(maxMovementCount)를 구함")
    @Test
    void maxMovementCount() {
        FakeRaceMap fakeRaceMap = FakeRaceMap.builder()
                    .goRace("a")
                    .stopRace("b")
                    .stopRace("c")
                .fakeRaceMap();

        RacingCarResults results = motorRacing(fakeRaceMap.racingCarNames())
                .allRacingCarRace(fakeRaceMap);

        assertThat(results.getMaxMovementCount()).isEqualTo(1);
    }

    @DisplayName("경주 진행이 끝나고 모든 레이싱카를 조사해서, 최대 이동 횟수를 기준으로 우승자를 조사함")
    @Test
    void winningRacingCarNames() {
        FakeRaceMap fakeRaceMap = FakeRaceMap.builder()
                .goRace("a")
                .stopRace("b")
                .goRace("c")
                .fakeRaceMap();

        RacingCarResults results = motorRacing(fakeRaceMap.racingCarNames())
                .allRacingCarRace(fakeRaceMap);

        assertThat(results.getMaxMovementCount()).isEqualTo(1);
        assertThat(results.getWinningRacingCarNames()).containsExactly("a", "c");
    }
}