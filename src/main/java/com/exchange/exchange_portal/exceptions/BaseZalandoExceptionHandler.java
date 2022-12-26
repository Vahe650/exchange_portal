package com.exchange.exchange_portal.exceptions;


import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;
import org.zalando.problem.violations.Violation;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestControllerAdvice
public class BaseZalandoExceptionHandler implements ProblemHandling, SecurityAdviceTrait {

    @Override
    public ResponseEntity<Problem> handleThrowable(Throwable throwable, NativeWebRequest request) {
        return ProblemHandling.super.handleThrowable(new UnhandledException(throwable), request);
    }

    @Override
    public ResponseEntity<Problem> handleAuthentication(AuthenticationException e, NativeWebRequest request) {
        return create(e, prepare(e, Status.UNAUTHORIZED, Problem.DEFAULT_TYPE).withTitle("Недостаточно прав")
                .withDetail("Недостаточно прав для доступа к ресурсу")
                .build(), request);
    }

    @Override
    public ProblemBuilder prepare(Throwable throwable, StatusType status, URI type) {
        return Problem.builder()
                .withType(type)
                .withTitle(status.getReasonPhrase())
                .withStatus(status)
                .withInstance(URI.create("urn:traceId:" + MDC.get("traceId")))
                .withDetail(throwable.getMessage())
                .withCause(Optional.ofNullable(throwable.getCause())
                        .filter(cause -> isCausalChainsEnabled())
                        .map(this::toProblem)
                        .orElse(null));
    }

    @Override
    public ResponseEntity<Problem> newConstraintViolationProblem(
            Throwable throwable, Collection<Violation> stream, NativeWebRequest request
    ) {
        Problem problem = new ConstraintViolationProblem(new ArrayList<>(stream));

        return create(throwable, problem, request);
    }
}
