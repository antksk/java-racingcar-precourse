package racingcar.domain.racingrule;

@FunctionalInterface
public interface RacingMoveRule {

    static RacingMoveRule defaultRacingMoveRule(){
        return new RandomRacingMoveRule();
    }

    boolean isMove();

}
