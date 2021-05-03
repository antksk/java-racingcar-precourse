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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (RacingCarResults racingCarResult : roundByRacingCarResults) {
            sb.append(racingCarResult).append("\n");
        }
        return String.join("\n\n", sb);
    }
}
