package dev.rafex.ether.http.problem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dev.rafex.ether.http.problem.exception.ProblemException;
import dev.rafex.ether.http.problem.mapper.ProblemHttpErrorMapper;
import dev.rafex.ether.http.problem.model.ProblemDetails;

class ProblemHttpErrorMapperTest {

    @Test
    void shouldMapProblemExceptionToHttpError() {
        final var mapper = new ProblemHttpErrorMapper();
        final var problem = ProblemDetails.builder().status(422).title("Validation failed").detail("name is required")
                .property("code", "validation_failed").build();

        final var mapped = mapper.map(new ProblemException(problem));
        assertEquals(422, mapped.status());
        assertEquals("validation_failed", mapped.code());
        assertEquals("name is required", mapped.message());
    }
}
