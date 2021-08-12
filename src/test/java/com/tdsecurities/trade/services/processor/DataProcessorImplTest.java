package com.tdsecurities.trade.services.processor;

import com.tdsecurities.trade.model.csv.RefData;
import com.tdsecurities.trade.model.csv.Trade;
import com.tdsecurities.trade.model.csv.Valuation;
import com.tdsecurities.trade.model.output.TradeAggregator;
import com.tdsecurities.trade.model.output.TradeData;
import com.tdsecurities.trade.services.processor.DataProcessorImpl;
import com.tdsecurities.trade.services.processor.DataProcessor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

class DataProcessorImplTest {

    private DataProcessor dataProcessor;

    @BeforeEach
    void setUp() {
        dataProcessor = new DataProcessorImpl();
    }

    @Test
    void testProcessTradesJoinedByColumn() {

        String inventory = "IRD_TOR_LONGTERM";
        Long tradeId = 6998156L;

        Trade trade = new Trade();
        trade.setInventory(inventory);
        trade.setTradeId(tradeId);
        RefData refData = new RefData();
        refData.setInventory(inventory);
        Valuation valuation = new Valuation();
        valuation.setTradeId(tradeId);

        TradeData tradeData = new TradeData(Lists.newArrayList(trade), Lists.newArrayList(refData), Lists.newArrayList(valuation));

        List<TradeAggregator> tradeAggregators = dataProcessor.processTrades(tradeData);

        assertThat(tradeAggregators).hasSize(1);
        assertThat(tradeAggregators).allSatisfy(tradeAggregator -> {
            assertThat(tradeAggregator.getTrade()).isNotNull();
            assertThat(tradeAggregator.getTrade().getInventory()).isEqualTo(inventory);
            assertThat(tradeAggregator.getTrade().getTradeId()).isEqualTo(tradeId);
            assertThat(tradeAggregator.getRefData()).isNotNull();
            assertThat(tradeAggregator.getRefData().getInventory()).isEqualTo(inventory);
            assertThat(tradeAggregator.getValuation()).isNotNull();
            assertThat(tradeAggregator.getValuation().getTradeId()).isEqualTo(tradeId);
        });
    }

    @Test
    void testProcessTradesNotJoinedByColumn() {

        String inventory = "IRD_TOR_LONGTERM";
        Long tradeId = 6998156L;

        Trade trade = new Trade();
        trade.setInventory(inventory);
        trade.setTradeId(tradeId);
        RefData refData = new RefData();
        Valuation valuation = new Valuation();

        TradeData tradeData = new TradeData(Lists.newArrayList(trade), Lists.newArrayList(refData), Lists.newArrayList(valuation));

        List<TradeAggregator> tradeAggregators = dataProcessor.processTrades(tradeData);

        assertThat(tradeAggregators).hasSize(1);
        assertThat(tradeAggregators).allSatisfy(tradeAggregator -> {
            assertThat(tradeAggregator.getTrade()).isNotNull();
            assertThat(tradeAggregator.getRefData()).isNull();
            assertThat(tradeAggregator.getValuation()).isNull();
        });
    }
}