package racingcar.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static racingcar.ui.MotorRacingDisplay.display;

public final class MotorRacingPlayerInput {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String CAR_NAME_SPLIT = ",";

    public static List<String> inputRacingCarNames(){
        display("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        return Arrays.asList(scanner.nextLine().split(CAR_NAME_SPLIT));
    }

    public static int inputTryRoundByRacingCount() {
        display("시도할 회수는 몇 회 인가요?");
        return scanner.nextInt();
    }
}
