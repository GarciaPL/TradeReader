package com.tdsecurities.trade.services.reader;

import com.tdsecurities.trade.model.output.TradeData;

public interface Reader {
    TradeData readData(ReaderContext readerContext);
}
