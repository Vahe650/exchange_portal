package com.exchange.exchange_portal.exceptions;

import org.zalando.problem.AbstractThrowableProblem;

import static org.zalando.problem.Status.NOT_FOUND;

public class NotFoundProblem extends AbstractThrowableProblem {

    private NotFoundProblem(String message) {
        super(null, message, NOT_FOUND, message);
    }

    public static NotFoundProblem with(String message) {
        return new NotFoundProblem(message);
    }



}
