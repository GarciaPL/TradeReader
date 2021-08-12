package com.tdsecurities.trade.services.reader;

public enum InputType {
    CSV;

    public boolean isCsv() {
        return this == CSV;
    }
}
