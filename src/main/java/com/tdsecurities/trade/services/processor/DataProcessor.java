package com.tdsecurities.trade.services.processor;

import com.tdsecurities.trade.model.output.TradeAggregator;
import com.tdsecurities.trade.model.output.TradeData;

import java.util.List;

public interface DataProcessor {
    List<TradeAggregator> processTrades(TradeData tradeData);
}
