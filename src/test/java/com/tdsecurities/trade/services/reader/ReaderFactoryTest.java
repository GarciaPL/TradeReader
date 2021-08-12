package com.tdsecurities.trade.services.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReaderFactoryTest {

    private ReaderFactory readerFactory;

    @BeforeEach
    void setUp() {
        readerFactory = new ReaderFactory();
    }

    @Test
    void testGetReaderForCsv() {
        Reader reader = readerFactory.getReader(InputType.CSV);

        assertThat(reader).isInstanceOf(CsvReader.class);
    }
}