package com.tdsecurities.trade.services.reader;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReaderContext {
    private String tradeFile;
    private String refDataFile;
    private String valuationFile;
}
