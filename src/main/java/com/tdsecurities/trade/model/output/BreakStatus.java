package com.tdsecurities.trade.model.output;

import lombok.Getter;
import org.apache.commons.lang3.Range;

import java.math.BigDecimal;
import java.util.Arrays;

@Getter
public enum BreakStatus {

    FIRST_HUNDRED(Range.between(BigDecimal.ZERO, BigDecimal.valueOf(99))),
    SECOND_HUNDRED(Range.between(BigDecimal.valueOf(100), BigDecimal.valueOf(999))),
    THIRD_HUNDRED(Range.between(BigDecimal.valueOf(1000), BigDecimal.valueOf(9999))),
    FOURTH_HUNDRED(Range.between(BigDecimal.valueOf(10000), BigDecimal.valueOf(99999))),
    INFINITY(Range.between(BigDecimal.valueOf(100000), BigDecimal.valueOf(999999999))),
    OUT_OF_RANGE(Range.between(BigDecimal.valueOf(-999999999), BigDecimal.valueOf(-1)));

    private final Range<BigDecimal> range;

    BreakStatus(Range<BigDecimal> range) {
        this.range = range;
    }

    public static BreakStatus getBreakStatusInRange(BigDecimal value) {
        return Arrays.stream(BreakStatus.values())
                .filter(breakStatus -> breakStatus.range.contains(value))
                .findFirst()
                .orElse(OUT_OF_RANGE);
    }

    public String getFormattedString() {
        return range.toString("%s-%s");
    }
}
