package com.tdsecurities.trade.services.processor;

import com.tdsecurities.trade.model.csv.RefData;
import com.tdsecurities.trade.model.csv.Trade;
import com.tdsecurities.trade.model.csv.Valuation;
import com.tdsecurities.trade.model.output.TradeAggregator;
import com.tdsecurities.trade.model.output.TradeData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DataProcessorImpl implements DataProcessor {

    @Override
    public List<TradeAggregator> processTrades(TradeData tradeData) {

        List<Trade> trades = tradeData.getTrades();
        List<RefData> refDatas = tradeData.getRefDatas();
        List<Valuation> valuations = tradeData.getValuations();

        Map<String, RefData> refDatasByInventory = refDatas.stream()
                .collect(Collectors.toMap(RefData::getInventory, Function.identity(), (refData, refData2) -> refData));

        List<TradeAggregator> tradesByRefData = trades.stream().reduce(new ArrayList<>(), (aggregators, trade) -> {
            if (refDatasByInventory.containsKey(trade.getInventory())) {
                aggregators.add(TradeAggregator.builder().trade(trade).refData(refDatasByInventory.get(trade.getInventory())).build());
            } else {
                aggregators.add(TradeAggregator.builder().trade(trade).build());
            }
            return aggregators;
        }, (acc1, acc2) -> {
            acc1.addAll(acc2);
            return acc1;
        });

        Map<Long, Valuation> valuationsByTradeId = valuations.stream()
                .collect(Collectors.toMap(Valuation::getTradeId, Function.identity(), (valuation, valuation2) -> valuation));

        List<TradeAggregator> aggregatedData = tradesByRefData.stream().reduce(new ArrayList<>(), (aggregators, tradeAggregatorOfTradesByRefData) -> {
            Long tradeId = tradeAggregatorOfTradesByRefData.getTrade().getTradeId();
            if (tradeAggregatorOfTradesByRefData.hasTrade() && valuationsByTradeId.containsKey(tradeId)) {
                aggregators.add(tradeAggregatorOfTradesByRefData.toBuilder().valuation(valuationsByTradeId.get(tradeId)).build());
            } else {
                aggregators.add(tradeAggregatorOfTradesByRefData);
            }
            return aggregators;
        }, (acc1, acc2) -> {
            acc1.addAll(acc2);
            return acc1;
        });

        log.info("Aggregation of {} trades completed successfully", aggregatedData.size());

        return aggregatedData;
    }

}
