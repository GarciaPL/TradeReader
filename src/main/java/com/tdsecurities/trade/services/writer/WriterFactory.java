package com.tdsecurities.trade.services.writer;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class WriterFactory {

    private Map<OutputType, WriterStrategy> strategies;

    @Autowired
    public WriterFactory(Set<WriterStrategy> writerStrategies) {
        createStrategy(writerStrategies);
    }

    public WriterStrategy findStrategy(OutputType outputType) {
        return strategies.get(outputType);
    }

    private void createStrategy(Set<WriterStrategy> writerStrategies) {
        strategies = Maps.newHashMap();
        writerStrategies.forEach(strategy -> strategies.put(strategy.getOutputType(), strategy));
    }

}
