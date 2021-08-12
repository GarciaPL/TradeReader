package com.tdsecurities.trade.services.writer;

public enum OutputType {
    CSV, CONSOLE;

    public boolean isCsv() {
        return this == CSV;
    }

    public boolean isConsole() {
        return this == CONSOLE;
    }
}
