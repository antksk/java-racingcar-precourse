package racingcar.domain;

import java.util.function.Consumer;

public final class RacingCarResult implements Comparable<RacingCarResult> {
    static final String RACING_CAR_MOVEMENT_RENDER_STYLE = "-";

    private final String racingCarName;
    private final int movementCount;

    public RacingCarResult(String racingCarName, int movementCount) {
        this.racingCarName = racingCarName;
        this.movementCount = movementCount;
    }

    private String toRenderMovingCount() {
        StringBuilder sb = new StringBuilder(movementCount);
        for (int i = 0; movementCount > i; ++i) {
            sb.append(RACING_CAR_MOVEMENT_RENDER_STYLE);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("%s : %s", racingCarName, toRenderMovingCount());
    }

    @Override
    public int compareTo(RacingCarResult racingCarResult) {
        return getMovementCount() - racingCarResult.getMovementCount();
    }

    String getRacingCarName(){
        return racingCarName;
    }

    public int getMovementCount() {
        return movementCount;
    }

    boolean equalsMovementCount(int movementCount){
        return getMovementCount() == movementCount;
    }

    void ifEqualsCountThenWinRacingCarNames(final int maxMovementCount, Consumer<String> winningRacingCarNamesConsumer){
        if (equalsMovementCount(maxMovementCount)){
            winningRacingCarNamesConsumer.accept(getRacingCarName());
        }
    }
}
