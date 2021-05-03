package racingcar;

import racingcar.domain.RoundByRacingCarResults;

import static racingcar.domain.core.RacingCars.motorRacing;
import static racingcar.ui.MotorRacingDisplay.roundByRacingCarResultsDisplay;
import static racingcar.ui.MotorRacingDisplay.winningRacingCarNamesDisplay;
import static racingcar.ui.MotorRacingPlayerInput.inputRacingCarNames;
import static racingcar.ui.MotorRacingPlayerInput.inputTryRoundByRacingCount;

public class RacingCarGameApplication {
    public static void main(String[] args) {
        final RoundByRacingCarResults roundByRacingCarResults = motorRacing(inputRacingCarNames())
                .roundByRacing(inputTryRoundByRacingCount());

        roundByRacingCarResultsDisplay(roundByRacingCarResults);
        winningRacingCarNamesDisplay(roundByRacingCarResults.toWinningRacingCarNames());
    }
}
