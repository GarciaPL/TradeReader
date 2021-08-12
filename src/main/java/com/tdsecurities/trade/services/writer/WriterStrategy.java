package com.tdsecurities.trade.services.writer;

import com.tdsecurities.trade.model.output.TradeAggregator;

import java.util.List;

public interface WriterStrategy {
    void write(List<TradeAggregator> aggregatedTrades);

    OutputType getOutputType();
}
