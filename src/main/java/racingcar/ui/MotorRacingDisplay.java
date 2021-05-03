package racingcar.ui;

import racingcar.domain.RoundByRacingCarResults;

import java.util.List;

public class MotorRacingDisplay {

    public static void roundByRacingCarResultsDisplay(RoundByRacingCarResults results){
        display("\n실행 결과");
        display(results);
    }

    public static void winningRacingCarNamesDisplay(List<String> winningRacingCarNames){
        display(String.join(", ", winningRacingCarNames) + "가 최종 우승했습니다.");
    }

    public static void display(Object o){
        display(o.toString());
    }

    public static void display(String msg){
        System.out.println(msg);
    }
}
