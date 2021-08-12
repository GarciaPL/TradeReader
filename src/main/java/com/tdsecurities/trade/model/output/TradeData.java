package com.tdsecurities.trade.model.output;

import com.tdsecurities.trade.model.csv.RefData;
import com.tdsecurities.trade.model.csv.Trade;
import com.tdsecurities.trade.model.csv.Valuation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class TradeData {

    private List<Trade> trades;
    private List<RefData> refDatas;
    private List<Valuation> valuations;
}
