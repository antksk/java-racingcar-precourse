package racingcar.domain.core;

import java.util.Objects;
import java.util.regex.Pattern;

final class RacingCarName {
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

    static RacingCarName racingCarName(String name) {
        return new RacingCarName(name.trim());
    }

    private void validName(String name) {
        if (!namePattern.matcher(name).matches()) {
            throw new IllegalArgumentException(ERROR_MSG_VALID_NAME);
        }
    }

    private void validMaxLengthName(String name) {
        if (MAX_LENGHT_NAME < name.length()) {
            throw new IllegalArgumentException(ERROR_MSG_MAX_LENGHT_NAME);
        }
    }

    private void requireName(String name) {
        if (null == name || "".equals(name.trim())) {
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

    public String toName() {
        return name;
    }
}
