package com.tdsecurities.trade.services.writer;

import com.tdsecurities.trade.model.output.BreakStatus;
import com.tdsecurities.trade.model.output.Term;
import com.tdsecurities.trade.model.output.TradeAggregator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.Function;

public class DataFunctors {

    public static final String DATE_FORMAT = "yyyyMMdd";

    static Function<TradeAggregator, String> getTopOfHouse() {
        return aggregator -> StringUtils.isEmpty(aggregator.getRefData().getTopOfHouse()) ? "" : aggregator.getRefData().getTopOfHouse();
    }

    static Function<TradeAggregator, String> getSegment() {
        return aggregator -> StringUtils.isEmpty(aggregator.getRefData().getSegment()) ? "" : aggregator.getRefData().getSegment();
    }

    static Function<TradeAggregator, String> getViceChair() {
        return aggregator -> StringUtils.isEmpty(aggregator.getRefData().getViceChair()) ? "" : aggregator.getRefData().getViceChair();
    }

    static Function<TradeAggregator, String> getGlobalBusiness() {
        return aggregator -> StringUtils.isEmpty(aggregator.getRefData().getGlobalBusiness()) ? "" : aggregator.getRefData().getGlobalBusiness();
    }

    static Function<TradeAggregator, String> getPolicy() {
        return aggregator -> StringUtils.isEmpty(aggregator.getRefData().getPolicy()) ? "" : aggregator.getRefData().getPolicy();
    }

    static Function<TradeAggregator, String> getDesk() {
        return aggregator -> StringUtils.isEmpty(aggregator.getRefData().getDesk()) ? "" : aggregator.getRefData().getDesk();
    }

    static Function<TradeAggregator, String> getPortfolio() {
        return aggregator -> StringUtils.isEmpty(aggregator.getRefData().getPortfolio()) ? "" : aggregator.getRefData().getPortfolio();
    }

    static Function<TradeAggregator, String> getBU() {
        return aggregator -> aggregator.getRefData().getBu() == null ? "" : aggregator.getRefData().getBu().toString();
    }

    static Function<TradeAggregator, String> getCLINE() {
        return aggregator -> StringUtils.isEmpty(aggregator.getRefData().getCline()) ? "" : aggregator.getRefData().getCline();
    }

    static Function<TradeAggregator, String> getInventory() {
        return aggregator -> StringUtils.isEmpty(aggregator.getRefData().getInventory()) ? "" : aggregator.getRefData().getInventory();
    }

    static Function<TradeAggregator, String> getBook() {
        return aggregator -> StringUtils.isEmpty(aggregator.getTrade().getBook()) ? "" : aggregator.getTrade().getBook();
    }

    static Function<TradeAggregator, String> getSystem() {
        return aggregator -> StringUtils.isEmpty(aggregator.getTrade().getSystem()) ? "" : aggregator.getTrade().getSystem();
    }

    static Function<TradeAggregator, String> getLegalEntity() {
        return aggregator -> StringUtils.isEmpty(aggregator.getTrade().getLegalEntity()) ? "" : aggregator.getTrade().getLegalEntity();
    }

    static Function<TradeAggregator, String> getTradeId() {
        return aggregator -> aggregator.getTrade().getTradeId() == null ? "" : aggregator.getTrade().getTradeId().toString();
    }

    static Function<TradeAggregator, String> getVersion() {
        return aggregator -> aggregator.getTrade().getVersion() == null ? "" : aggregator.getTrade().getVersion().toString();
    }

    static Function<TradeAggregator, String> getTradeStatus() {
        return aggregator -> StringUtils.isEmpty(aggregator.getTrade().getTradeStatus()) ? "" : aggregator.getTrade().getTradeStatus();
    }

    static Function<TradeAggregator, String> getProductType() {
        return aggregator -> StringUtils.isEmpty(aggregator.getTrade().getProductType()) ? "" : aggregator.getTrade().getProductType();
    }

    static Function<TradeAggregator, String> getResettingLeg() {
        return aggregator -> StringUtils.isEmpty(aggregator.getTrade().getResettingLeg()) ? "" : aggregator.getTrade().getResettingLeg();
    }

    static Function<TradeAggregator, String> getProductSubType() {
        return aggregator -> StringUtils.isEmpty(aggregator.getTrade().getProductSubType()) ? "" : aggregator.getTrade().getProductSubType();
    }

    static Function<TradeAggregator, String> getTdsProductType() {
        return aggregator -> StringUtils.isEmpty(aggregator.getTrade().getTdsProductType()) ? "" : aggregator.getTrade().getTdsProductType();
    }

    static Function<TradeAggregator, String> getSecCodeSubType() {
        return aggregator -> StringUtils.isEmpty(aggregator.getTrade().getSecCodeSubType()) ? "" : aggregator.getTrade().getSecCodeSubType();
    }

    static Function<TradeAggregator, String> getSwapType() {
        return aggregator -> StringUtils.isEmpty(aggregator.getTrade().getSwapType()) ? "" : aggregator.getTrade().getSwapType();
    }

    static Function<TradeAggregator, String> getDescription() {
        return aggregator -> StringUtils.isEmpty(aggregator.getTrade().getDescription()) ? "" : aggregator.getTrade().getDescription();
    }

    static Function<TradeAggregator, String> getTradeDate() {
        return aggregator -> StringUtils.isEmpty(aggregator.getTrade().getTradeDate()) ? "" : aggregator.getTrade().getTradeDate();
    }

    static Function<TradeAggregator, String> getStartDate() {
        return aggregator -> StringUtils.isEmpty(aggregator.getTrade().getStartDate()) ? "" : aggregator.getTrade().getStartDate();
    }

    static Function<TradeAggregator, String> getMaturityDate() {
        return aggregator -> StringUtils.isEmpty(aggregator.getTrade().getMaturityDate()) ? "" : aggregator.getTrade().getMaturityDate();
    }

    static Function<TradeAggregator, String> getUqlOcMmbMs() {
        return aggregator -> {
            if (aggregator.hasValuation()) {
                return aggregator.getValuation().getUqlOcMmbMs() == null ? "" : aggregator.getValuation().getUqlOcMmbMs().toPlainString();
            }
            return "";
        };
    }

    static Function<TradeAggregator, String> getUqlOcMmbMsPc() {
        return aggregator -> {
            if (aggregator.hasValuation()) {
                return aggregator.getValuation().getUqlOcMmbMsPc() == null ? "" : aggregator.getValuation().getUqlOcMmbMsPc().toPlainString();
            }
            return "";
        };
    }

    static Function<TradeAggregator, String> getMsPc() {
        return aggregator -> {
            if (aggregator.hasValuation()) {
                return getMsPc(aggregator);
            }
            return "";
        };
    }

    private static String getMsPc(TradeAggregator aggregator) {
        BigDecimal uqlOcMmbMs = aggregator.getValuation().getUqlOcMmbMs();
        BigDecimal uqlOcMmbMsPc = aggregator.getValuation().getUqlOcMmbMsPc();
        if (uqlOcMmbMs == null || uqlOcMmbMsPc == null) {
            return "";
        }
        if (uqlOcMmbMs.compareTo(uqlOcMmbMsPc) > 0) {
            return uqlOcMmbMs.subtract(uqlOcMmbMsPc, MathContext.DECIMAL64).toPlainString();
        } else if (uqlOcMmbMs.compareTo(uqlOcMmbMsPc) < 0) {
            return uqlOcMmbMsPc.subtract(uqlOcMmbMs, MathContext.DECIMAL64).toPlainString();
        } else {
            return uqlOcMmbMs.subtract(uqlOcMmbMsPc, MathContext.DECIMAL64).toPlainString();
        }
    }

    static Function<TradeAggregator, String> getBreakStatus() {
        return aggregator -> {
            if (aggregator.hasValuation()) {
                String msPc = getMsPc(aggregator);
                return BreakStatus.getBreakStatusInRange(NumberUtils.createBigDecimal(msPc)).getFormattedString();
            }
            return "";
        };
    }

    static Function<TradeAggregator, String> getTerm() {
        return aggregator -> {
            if (aggregator.hasValuation()) {
                DateTime maturityDate;
                try {
                    DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
                    maturityDate = formatter.parseDateTime(aggregator.getTrade().getMaturityDate());
                } catch (Exception e) {
                    return "";
                }
                DateTime today = DateTime.now();
                if (maturityDate.isBefore(today) || maturityDate.isEqual(today)) {
                    return "";
                }
                return Term.getTerm(today, maturityDate);
            }
            return "";
        };
    }
}
