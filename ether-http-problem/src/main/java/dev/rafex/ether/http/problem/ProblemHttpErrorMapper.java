package dev.rafex.ether.http.problem;

import java.util.Objects;

import dev.rafex.ether.http.core.DefaultErrorMapper;
import dev.rafex.ether.http.core.ErrorMapper;
import dev.rafex.ether.http.core.HttpError;

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
		if (error instanceof ProblemException problemException && problemException.problem() != null) {
			final var problem = problemException.problem();
			return new HttpError(problem.status(), problemCode(problem), safe(problem.detail(), problem.title()));
		}
		return fallback.map(error);
	}

	private static String problemCode(final ProblemDetails problem) {
		final var code = problem.properties().get("code");
		if (code instanceof String value && !value.isBlank()) {
			return value;
		}
		return "problem";
	}

	private static String safe(final String detail, final String fallback) {
		if (detail != null && !detail.isBlank()) {
			return detail;
		}
		return fallback == null || fallback.isBlank() ? "problem" : fallback;
	}
}
