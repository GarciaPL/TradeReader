package com.tdsecurities.trade.model.output;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TermTest {

    @Test
    void testGetTermOfRangeOneToSixMonths() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");
        DateTime today = formatter.parseDateTime("20210303");
        DateTime maturity = formatter.parseDateTime("20210703");

        String term = Term.getTerm(today, maturity);

        assertThat(Term.ONE_SIX_MONTHS.getRepresentation()).isEqualTo(term);
    }

    @Test
    void testGetTermOfRangeOneToTenYears() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");
        DateTime today = formatter.parseDateTime("20210303");
        DateTime maturity = formatter.parseDateTime("20220703");

        String term = Term.getTerm(today, maturity);

        assertThat(Term.ONE_TEN_YEARS.getRepresentation()).isEqualTo(term);
    }
}