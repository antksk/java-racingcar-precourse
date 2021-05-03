package racingcar.domain.core;

import racingcar.domain.RacingCarResult;
import racingcar.domain.RacingCarResults;
import racingcar.domain.RoundByRacingCarResults;
import racingcar.domain.racingrule.RacingMoveRule;

import java.util.*;
import java.util.function.Consumer;

import static racingcar.domain.core.RacingCar.raceAttend;
import static racingcar.domain.racingrule.RacingMoveRule.defaultRacingMoveRule;

public class RacingCars {
    static final String ERROR_MSG_EMPTY_CAR_NAMES = "자동차 이름 목록을 등록해주세요.";
    static final String ERROR_MSG_DUPLICATE_CAR_NAMES = "중복된 자동차 이름을 사용할수 없습니다.";
    private final List<RacingCar> racingCars;
    private final RacingMoveRule racingMoveRule;

    private RacingCars(List<RacingCar> racingCars, RacingMoveRule racingMoveRule) {
        this.racingCars = racingCars;
        this.racingMoveRule = racingMoveRule;
    }

    static RacingCars motorRacing(List<String> names, RacingMoveRule racingMoveRule){
        validRacingCarNames(names);
        return new RacingCars(toRacingCarList(names),  racingMoveRule);
    }

    public static RacingCars motorRacing(List<String> names){
        return motorRacing(names,  defaultRacingMoveRule());
    }

    static List<RacingCar> toRacingCarList(List<String> names){
        List<RacingCar> racingCars = new ArrayList<>(names.size());
        for (String name : names) {
            racingCars.add(raceAttend(name));
        }
        return Collections.unmodifiableList(racingCars);
    }

    private static void validRacingCarNames(List<String> carNames) {
        if (null == carNames || carNames.isEmpty()){
            throw new IllegalArgumentException(ERROR_MSG_EMPTY_CAR_NAMES);
        }
        if (carNames.size() != new HashSet<>(carNames).size()){
            throw new IllegalArgumentException(ERROR_MSG_DUPLICATE_CAR_NAMES);
        }
    }

    RacingCarResults allRacingCarRace(){
        RacingCarResults racingCarResults = new RacingCarResults(racingCars.size());
        for (RacingCar racingCar : racingCars) {
            RacingCarResult racingCarResult = racingCar.racing(racingMoveRule).toResult();
            racingCarResults.add(racingCarResult);
        }
        return racingCarResults;
    }

    RacingCarResults allRacingCarRace(FakeRaceMap fakeRaceMap){
        RacingCarResults racingCarResults = new RacingCarResults(racingCars.size());
        for (RacingCar racingCar : racingCars) {
            fakeRaceMap.ifFakeRaceToRacingCarResult(racingCar, racingCarResults::add);
        }
        return racingCarResults;
    }

    public RoundByRacingCarResults roundByRacing(final int raceRound){
        final RoundByRacingCarResults roundByRacingCarResults = new RoundByRacingCarResults(raceRound);
        for(int round = 0; raceRound > round; ++round) {
            roundByRacingCarResults.add(allRacingCarRace());
        }
        return roundByRacingCarResults;
    }
}
