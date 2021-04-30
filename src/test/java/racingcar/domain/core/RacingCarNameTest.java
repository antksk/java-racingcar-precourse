package racingcar.domain.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static racingcar.domain.core.RacingCarName.*;

public class RacingCarNameTest {

    @DisplayName("자동차 이름을 등록해야 함")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "null"})
    void requiredName(String carName) {
        String testName = "null".equals(carName) ? null : carName;
        assertThatIllegalArgumentException()
                .isThrownBy(()->name(testName));
    }

    @DisplayName("자동차 이름은 MAX_LENGTH이하로 등록해야 함")
    @ParameterizedTest
    @ValueSource(strings = {"abcdef", "111aaa"})
    void maxLengthName(String carName) {
        assertThatIllegalArgumentException()
                .isThrownBy(()->name(carName))
                .withMessage(ERROR_MSG_MAX_LENGHT_NAME);
    }

    @DisplayName("자동차 이름은 소문자와 숫자, 특정 특수문자(-,_)로 등록해야 함")
    @ParameterizedTest
    @ValueSource(strings = {"A", "abb*1", "1abcd", "a123@", "____1", "----1"})
    void validName(String carName){
        assertThatIllegalArgumentException()
                .isThrownBy(()->name(carName))
                .withMessage(ERROR_MSG_VALID_NAME);
    }

}
