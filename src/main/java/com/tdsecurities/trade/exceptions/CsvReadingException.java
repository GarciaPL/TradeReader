package com.tdsecurities.trade.exceptions;

public class CsvReadingException extends RuntimeException {

    public CsvReadingException(String message) {
        super(message);
    }

    public CsvReadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
