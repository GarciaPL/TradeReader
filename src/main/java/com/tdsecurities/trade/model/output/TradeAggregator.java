package com.tdsecurities.trade.model.output;

import com.tdsecurities.trade.model.csv.RefData;
import com.tdsecurities.trade.model.csv.Trade;
import com.tdsecurities.trade.model.csv.Valuation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
public class TradeAggregator {

    private Trade trade;
    private RefData refData;
    private Valuation valuation;

    public boolean hasTrade() {
        return trade != null;
    }

    public boolean hasValuation() {
        return valuation != null;
    }

}
