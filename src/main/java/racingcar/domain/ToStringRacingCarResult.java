package racingcar.domain;

import java.util.List;

final class ToStringRacingCarResult {
    private static final String NEW_LINE = "\n";
    private final StringBuilder stringBuilder;

    ToStringRacingCarResult() {
        stringBuilder = new StringBuilder();
    }

    ToStringRacingCarResult result(Object o) {
        stringBuilder.append(o);
        return this;
    }

    ToStringRacingCarResult forEachWithResultAndNewLine(List<?> results) {
        results.forEach(this::resultAndNewLine);
        return this;
    }

    private void resultAndNewLine(Object o) {
        result(o).newLine();
    }

    ToStringRacingCarResult newLine(){
        return newLine(1);
    }

    ToStringRacingCarResult newLine(int repeat) {
        for (int i = 0; i < repeat; i++) {
            stringBuilder.append(NEW_LINE);
        }
        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
