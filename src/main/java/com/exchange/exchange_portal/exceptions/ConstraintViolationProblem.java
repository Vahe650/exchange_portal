package com.exchange.exchange_portal.exceptions;

import org.slf4j.MDC;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.violations.Violation;

import java.net.URI;
import java.util.List;

public class ConstraintViolationProblem extends ThrowableProblem {
    private static final URI TYPE = URI.create("https://zalando.github.io/problem/constraint-violation");

    private final List<Violation> violations;

    public ConstraintViolationProblem(List<Violation> violations) {
        this.violations = violations;
    }

    @Override
    public URI getType() {
        return TYPE;
    }

    @Override
    public String getTitle() {
        return "Ошибка валидации";
    }

    @Override
    public StatusType getStatus() {
        return Status.BAD_REQUEST;
    }

    @Override
    public URI getInstance() {
        return URI.create("urn:traceId:" + MDC.get("traceId"));
    }

    public List<Violation> getViolations() {
        return violations;
    }

}
