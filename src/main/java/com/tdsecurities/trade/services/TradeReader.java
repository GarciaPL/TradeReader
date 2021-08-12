package com.tdsecurities.trade.services;

import com.tdsecurities.trade.model.output.TradeAggregator;
import com.tdsecurities.trade.model.output.TradeData;
import com.tdsecurities.trade.services.processor.DataProcessor;
import com.tdsecurities.trade.services.reader.InputType;
import com.tdsecurities.trade.services.reader.Reader;
import com.tdsecurities.trade.services.reader.ReaderContext;
import com.tdsecurities.trade.services.reader.ReaderFactory;
import com.tdsecurities.trade.services.writer.OutputType;
import com.tdsecurities.trade.services.writer.WriterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeReader {

    private final ReaderFactory readerFactory;
    private final DataProcessor dataProcessor;
    private final WriterFactory writerFactory;

    @Autowired
    public TradeReader(ReaderFactory readerFactory, DataProcessor dataProcessor, WriterFactory writerFactory) {
        this.readerFactory = readerFactory;
        this.dataProcessor = dataProcessor;
        this.writerFactory = writerFactory;
    }

    public void processTrades(OutputType outputType, ReaderContext readerContext) {
        Reader reader = readerFactory.getReader(InputType.CSV);
        TradeData tradeData = reader.readData(readerContext);
        List<TradeAggregator> tradeAggregators = dataProcessor.processTrades(tradeData);
        writerFactory.findStrategy(outputType).write(tradeAggregators);
    }
}
