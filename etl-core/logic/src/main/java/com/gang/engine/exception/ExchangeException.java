package com.gang.engine.exception;

import lombok.Data;

@Data
public class ExchangeException extends RuntimeException {

    private ExchangeErrorEnum errorCodEnum;

    public ExchangeException() {
        super();
    }

    public ExchangeException(String message) {
        super(message);
    }

    public ExchangeException(String message, ExchangeErrorEnum errorCodEnum) {
        super(message);
    }

    public ExchangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExchangeException(Throwable cause) {
        super(cause);
    }

    protected ExchangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getErrorMsg() {
        return this.errorCodEnum.getErrorMsg();
    }

    public String getErrorCode() {
        return this.errorCodEnum.getErrorCode();
    }
}
