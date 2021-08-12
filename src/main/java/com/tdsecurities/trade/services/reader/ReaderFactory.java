package com.tdsecurities.trade.services.reader;

import org.springframework.stereotype.Service;

@Service
public class ReaderFactory {

    public Reader getReader(InputType inputType) {
        if (inputType.isCsv()) {
            return new CsvReader();
        }

        return new CsvReader();
    }
}
