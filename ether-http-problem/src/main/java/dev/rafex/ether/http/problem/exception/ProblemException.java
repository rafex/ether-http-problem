package dev.rafex.ether.http.problem.exception;

import dev.rafex.ether.http.problem.model.ProblemDetails;

public class ProblemException extends RuntimeException {

    private final ProblemDetails problem;

    public ProblemException(final ProblemDetails problem) {
        super(problem == null ? null : problem.detail());
        this.problem = problem;
    }

    public ProblemDetails problem() {
        return problem;
    }
}
