package racingcar.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RacingCarResults {
    private final List<RacingCarResult> racingCarResults;

    public RacingCarResults(int racingCarSize) {
        this.racingCarResults = new ArrayList<>(racingCarSize);
    }

    public void add(RacingCarResult racingCarResult){
        racingCarResults.add(racingCarResult);
    }

    public int getMaxMovementCount(){
        return Collections.max(racingCarResults)
                .getMovementCount();
    }

    public List<String> getWinningRacingCarNames(){
        List<String> winningRacingCarNames = new ArrayList<>();
        final int maxMovementCount = getMaxMovementCount();
        for (RacingCarResult racingCarResult : racingCarResults) {
            racingCarResult.ifEqualsCountThenWinRacingCarNames(maxMovementCount, winningRacingCarNames::add);
        }
        return Collections.unmodifiableList(winningRacingCarNames);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (RacingCarResult racingCarResult : racingCarResults) {
            sb.append(racingCarResult).append("\n");
        }
        return String.join("\n\n", sb);
    }
}
