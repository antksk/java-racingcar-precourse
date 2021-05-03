package racingcar.domain.core;

import racingcar.domain.RacingCarResult;
import racingcar.domain.racingrule.RacingMoveRule;

import java.util.*;
import java.util.function.Consumer;

final class FakeRaceMap {
    static final RacingMoveRule GO_RACE = ()-> true;
    static final RacingMoveRule STOP_RACE = ()-> false;

    private final Map<String, RacingMoveRule> fakeRaceMap;

    private FakeRaceMap(Map<String, RacingMoveRule> fakeRaceMap) {
        this.fakeRaceMap = fakeRaceMap;
    }

    static FakeRaceMapBuilder builder(){
        return new FakeRaceMapBuilder();
    }

    List<String> racingCarNames(){
        return Collections.unmodifiableList(new ArrayList<>(fakeRaceMap.keySet()));
    }

    private boolean hasRacingCarName(String racingCarName){
        return fakeRaceMap.containsKey(racingCarName);
    }

    private RacingCarResult fakeRaceOfRacingCar(String racingCarName, RacingCar racingCar){
        RacingMoveRule mappingRacingMoveRule = fakeRaceMap.get(racingCarName);
        return racingCar.racing(mappingRacingMoveRule).toResult();
    }

    void ifFakeRaceToRacingCarResult(RacingCar racingCar, Consumer<RacingCarResult> racingCarResultConsumer){
        String racingCarName = racingCar.name();
        if (hasRacingCarName(racingCarName)){
            racingCarResultConsumer.accept(fakeRaceOfRacingCar(racingCarName, racingCar));
        }
    }

    static final class FakeRaceMapBuilder{
        private final Map<String, RacingMoveRule> buildingFakeRaceMap;

        FakeRaceMapBuilder() {
            this.buildingFakeRaceMap = new HashMap<>();
        }

        FakeRaceMapBuilder goRace(String racingCarName){
            buildingFakeRaceMap.put(racingCarName, GO_RACE);
            return this;
        }

        FakeRaceMapBuilder stopRace(String racingCarName){
            buildingFakeRaceMap.put(racingCarName, STOP_RACE);
            return this;
        }

        FakeRaceMap fakeRaceMap(){
            return new FakeRaceMap(Collections.unmodifiableMap(buildingFakeRaceMap));
        }
    }
}
