package racingcar.domain.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Objects;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static racingcar.domain.core.RacingCarNameTest.RacingCarName.*;

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

    static final class RacingCarName {
        private static final int MAX_LENGHT_NAME = 5;
        static final String ERROR_MSG_MAX_LENGHT_NAME = "자동차 이름은 5자 이하로 등록해야 합니다.";
        static final String ERROR_MSG_VALID_NAME = "영어 소문자(a-z)로 시작하고, 특정 특수문자(-,_) 숫자를 포함할 수 있습니다";
        private static final Pattern namePattern = Pattern.compile("[a-z][a-z0-9_-]{0,4}");
        private final String name;

        RacingCarName(String name) {
            requireName(name);
            validMaxLengthName(name);
            validName(name);
            this.name = name;
        }

        static RacingCarName name(String name){
            return new RacingCarName(name);
        }

        private void validName(String name){
            if (!namePattern.matcher(name).matches()){
                throw new IllegalArgumentException(ERROR_MSG_VALID_NAME);
            }
        }

        private void validMaxLengthName(String name) {
            if (MAX_LENGHT_NAME < name.length()){
                throw new IllegalArgumentException(ERROR_MSG_MAX_LENGHT_NAME);
            }
        }

        private void requireName(String name) {
            if (null == name || "".equals(name.trim())){
                throw new IllegalArgumentException();
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RacingCarName that = (RacingCarName) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name);
        }

        @Override
        public String toString(){
            return name;
        }
    }
}
