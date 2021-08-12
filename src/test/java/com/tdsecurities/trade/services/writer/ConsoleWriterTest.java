package com.tdsecurities.trade.services.writer;

import com.google.common.collect.Lists;
import com.tdsecurities.trade.model.csv.RefData;
import com.tdsecurities.trade.model.csv.Trade;
import com.tdsecurities.trade.model.csv.Valuation;
import com.tdsecurities.trade.model.output.TradeAggregator;
import com.tdsecurities.trade.model.output.TradeData;
import com.tdsecurities.trade.services.processor.DataProcessor;
import com.tdsecurities.trade.services.processor.DataProcessorImpl;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.jeasy.random.FieldPredicates.*;

class ConsoleWriterTest {

    private DataProcessor dataProcessor;
    private ConsoleWriter consoleWriter;
    private EasyRandom generator;

    @BeforeEach
    void setUp() {
        dataProcessor = new DataProcessorImpl();
        consoleWriter = new ConsoleWriter();

        EasyRandomParameters parameters = new EasyRandomParameters()
                .excludeField(named("maturityDate").and(ofType(String.class)).and(inClass(Trade.class)))
                .excludeField(named("inventory").and(ofType(String.class)).and(inClass(Trade.class)))
                .excludeField(named("tradeId").and(ofType(String.class)).and(inClass(Trade.class)))
                .excludeField(named("tradeId").and(ofType(String.class)).and(inClass(Trade.class)))
                .excludeField(named("inventory").and(ofType(String.class)).and(inClass(RefData.class)))
                .excludeField(named("tradeId").and(ofType(String.class)).and(inClass(Valuation.class)));
        generator = new EasyRandom(parameters);
    }

    @Test
    void testConsole() {

        String inventory = "IRD_TOR_LONGTERM";
        Long tradeId = 6998156L;

        Trade trade = generator.nextObject(Trade.class);
        trade.setInventory(inventory);
        trade.setTradeId(tradeId);
        trade.setMaturityDate("20250505");
        RefData refData = generator.nextObject(RefData.class);
        refData.setInventory(inventory);
        Valuation valuation = generator.nextObject(Valuation.class);
        valuation.setTradeId(tradeId);

        TradeData tradeData = new TradeData(Lists.newArrayList(trade), Lists.newArrayList(refData), Lists.newArrayList(valuation));

        List<TradeAggregator> tradeAggregators = dataProcessor.processTrades(tradeData);

        consoleWriter.write(tradeAggregators);
    }
}