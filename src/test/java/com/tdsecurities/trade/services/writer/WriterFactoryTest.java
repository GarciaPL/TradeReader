package com.tdsecurities.trade.services.writer;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class WriterFactoryTest {

    private WriterFactory writerFactory;

    @BeforeEach
    void setUp() {
        Set<WriterStrategy> writerStrategies = Sets.newHashSet(new CsvWriter(), new ConsoleWriter());
        writerFactory = new WriterFactory(writerStrategies);
    }

    @Test
    void testGetWriteFactoryOfCsv() {
        WriterStrategy strategy = writerFactory.findStrategy(OutputType.CSV);

        assertThat(strategy.getOutputType()).isEqualTo(OutputType.CSV);
        assertThat(strategy).isInstanceOf(CsvWriter.class);
    }

    @Test
    void testGetWriteFactoryOfConsole() {
        WriterStrategy strategy = writerFactory.findStrategy(OutputType.CONSOLE);

        assertThat(strategy.getOutputType()).isEqualTo(OutputType.CONSOLE);
        assertThat(strategy).isInstanceOf(ConsoleWriter.class);
    }
}