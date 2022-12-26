package com.exchange.exchange_portal.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Unhandled exception")
public class UnhandledException extends RuntimeException {
    public UnhandledException(Throwable cause) {
        super(cause);
    }
}
