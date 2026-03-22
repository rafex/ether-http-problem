package dev.rafex.ether.http.problem.mapper;

import java.util.Objects;

import dev.rafex.ether.http.core.DefaultErrorMapper;
import dev.rafex.ether.http.core.ErrorMapper;
import dev.rafex.ether.http.core.HttpError;
import dev.rafex.ether.http.problem.exception.ProblemException;
import dev.rafex.ether.http.problem.model.ProblemDetails;

public final class ProblemHttpErrorMapper implements ErrorMapper {

    private final ErrorMapper fallback;

    public ProblemHttpErrorMapper() {
        this(new DefaultErrorMapper());
    }

    public ProblemHttpErrorMapper(final ErrorMapper fallback) {
        this.fallback = Objects.requireNonNull(fallback, "fallback");
    }

    @Override
    public HttpError map(final Throwable error) {
        return switch (error) {
            case ProblemException pe when pe.problem() != null -> {
                final var problem = pe.problem();
                yield new HttpError(problem.status(), problemCode(problem), safe(problem.detail(), problem.title()));
            }
            default -> fallback.map(error);
        };
    }

    private static String problemCode(final ProblemDetails problem) {
        return switch (problem.properties().get("code")) {
            case String value when !value.isBlank() -> value;
            default -> "problem";
        };
    }

    private static String safe(final String detail, final String fallback) {
        if (detail != null && !detail.isBlank()) {
            return detail;
        }
        return fallback == null || fallback.isBlank() ? "problem" : fallback;
    }
}
