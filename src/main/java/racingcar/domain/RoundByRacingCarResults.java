package racingcar.domain;

import java.util.ArrayList;
import java.util.List;

public final class RoundByRacingCarResults {
    private final List<RacingCarResults> roundByRacingCarResults;

    public RoundByRacingCarResults(int raceRound) {
        this.roundByRacingCarResults = new ArrayList<>(raceRound);
    }

    public void add(RacingCarResults racingCarResults){
        roundByRacingCarResults.add(racingCarResults);
    }

    private RacingCarResults lastRacingCarResults(){
        int lastIndex = roundByRacingCarResults.size() - 1;
        return roundByRacingCarResults.get(lastIndex);
    }

    public List<String> toWinningRacingCarNames(){
        return lastRacingCarResults().getWinningRacingCarNames();
    }

    @Override
    public String toString() {
        return new ToStringRacingCarResult()
                .forEachWithResultAndNewLine(roundByRacingCarResults)
              .toString();
    }

}
