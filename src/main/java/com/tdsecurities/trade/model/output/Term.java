package com.tdsecurities.trade.model.output;

import lombok.Getter;
import org.apache.commons.lang3.Range;
import org.joda.time.DateTime;
import org.joda.time.Months;

import java.util.Arrays;
import java.util.Optional;

@Getter
public enum Term {
    ZERO_ONE_MONTH(Range.between(0, 1), "0m-1m"),
    ONE_SIX_MONTHS(Range.between(1, 6), "1m-6m"),
    SIX_TWELVE_MONTHS(Range.between(6, 12), "6m-1yr"),
    ONE_TEN_YEARS(Range.between(12, 10 * 12), "1yr-10yr"),
    TEN_THIRTY_YEARS(Range.between(10 * 12, 30 * 12), "10yr-30yr"),
    THIRTY_FIFTY_YEARS(Range.between(30 * 12, 50 * 12), "30yr-50yr"),
    FIFTY_PLUS_YEARS(Range.between(50 * 12, 1000 * 12), "50yr+");

    private final Range<Integer> range;
    private final String representation;

    Term(Range<Integer> range, String representation) {
        this.range = range;
        this.representation = representation;
    }

    public static String getTerm(DateTime today, DateTime maturityDate) {
        int months = Months.monthsBetween(today.withTimeAtStartOfDay(), maturityDate.withTimeAtStartOfDay()).getMonths();
        Optional<Term> foundTerm = Arrays.stream(Term.values())
                .filter(term -> term.range.contains(months))
                .findFirst();

        if (foundTerm.isPresent()) {
            return foundTerm.get().getRepresentation();
        } else {
            return "";
        }
    }
}
