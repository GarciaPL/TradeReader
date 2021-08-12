package com.tdsecurities.trade.services.reader;

import com.tdsecurities.trade.model.output.TradeData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tdsecurities.trade.constants.CsvBundledFiles.*;
import static org.assertj.core.api.Assertions.assertThat;

class CsvReaderTest {

    private Reader reader;

    @BeforeEach
    void setUp() {
        reader = new CsvReader();
    }

    @Test
    void testReadData() {
        ReaderContext readerContext = new ReaderContext(TRADE_CSV, REF_DATA_CSV, VALUATION_CSV);
        TradeData tradeData = reader.readData(readerContext);

        assertThat(tradeData.getTrades()).isNotEmpty();
        assertThat(tradeData.getRefDatas()).isNotEmpty();
        assertThat(tradeData.getValuations()).isNotEmpty();
    }
}