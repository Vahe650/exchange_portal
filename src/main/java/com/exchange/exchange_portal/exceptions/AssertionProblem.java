package com.exchange.exchange_portal.exceptions;

import org.zalando.problem.AbstractThrowableProblem;

import static org.zalando.problem.Status.BAD_REQUEST;

public class AssertionProblem extends AbstractThrowableProblem {

    private AssertionProblem(String message) {
        super(null, message, BAD_REQUEST, "Предусловие не выполнено");
    }

    public static AssertionProblem with(String message) {
        return new AssertionProblem(message);
    }
}
