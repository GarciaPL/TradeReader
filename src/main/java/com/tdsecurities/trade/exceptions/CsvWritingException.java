package com.tdsecurities.trade.exceptions;

public class CsvWritingException extends RuntimeException {

    public CsvWritingException(String message) {
        super(message);
    }

    public CsvWritingException(String message, Throwable cause) {
        super(message, cause);
    }
}
