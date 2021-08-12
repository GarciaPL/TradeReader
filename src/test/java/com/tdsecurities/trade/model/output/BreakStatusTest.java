package com.tdsecurities.trade.model.output;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class BreakStatusTest {

    @Test
    void testValueInFirstHundred() {
        BreakStatus breakStatusInRange = BreakStatus.getBreakStatusInRange(BigDecimal.valueOf(5));

        assertThat(breakStatusInRange).isEqualByComparingTo(BreakStatus.FIRST_HUNDRED);
    }

    @Test
    void testValueIsOutOfRange() {
        BreakStatus breakStatusInRange = BreakStatus.getBreakStatusInRange(BigDecimal.valueOf(-5));

        assertThat(breakStatusInRange).isEqualByComparingTo(BreakStatus.OUT_OF_RANGE);
    }
}