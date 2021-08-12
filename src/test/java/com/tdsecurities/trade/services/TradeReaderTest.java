package com.tdsecurities.trade.services;

import com.google.common.collect.Sets;
import com.tdsecurities.trade.services.processor.DataProcessor;
import com.tdsecurities.trade.services.processor.DataProcessorImpl;
import com.tdsecurities.trade.services.reader.ReaderContext;
import com.tdsecurities.trade.services.reader.ReaderFactory;
import com.tdsecurities.trade.services.writer.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.tdsecurities.trade.constants.CsvBundledFiles.*;

class TradeReaderTest {

    private ReaderFactory readerFactory;
    private DataProcessor dataProcessor;
    private WriterFactory writerFactory;
    private TradeReader tradeReader;

    @BeforeEach
    void setUp() {
        readerFactory = new ReaderFactory();
        dataProcessor = new DataProcessorImpl();
        Set<WriterStrategy> writerStrategies = Sets.newHashSet(new CsvWriter(), new ConsoleWriter());
        writerFactory = new WriterFactory(writerStrategies);
        tradeReader = new TradeReader(readerFactory, dataProcessor, writerFactory);
    }

    @Test
    void testProcessTradesConsole() {
        ReaderContext readerContext = new ReaderContext(TRADE_CSV, REF_DATA_CSV, VALUATION_CSV);
        tradeReader.processTrades(OutputType.CONSOLE, readerContext);
    }

    @Test
    void testProcessTradesCsv() {
        ReaderContext readerContext = new ReaderContext(TRADE_CSV, REF_DATA_CSV, VALUATION_CSV);
        tradeReader.processTrades(OutputType.CSV, readerContext);
    }
}