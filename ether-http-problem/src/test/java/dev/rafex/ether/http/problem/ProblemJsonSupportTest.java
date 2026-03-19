package dev.rafex.ether.http.problem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.Test;

import dev.rafex.ether.json.JsonCodecBuilder;

class ProblemJsonSupportTest {

	@Test
	void shouldSerializeAndParseProblemDetails() {
		final var codec = JsonCodecBuilder.strict().build();
		final var problem = ProblemDetails.builder()
				.type(URI.create("https://example.com/problems/out-of-credit"))
				.title("Out of credit")
				.status(403)
				.detail("Balance is below zero")
				.property("code", "out_of_credit")
				.build();

		final var json = ProblemJsonSupport.toJson(codec, problem);
		final var parsed = ProblemJsonSupport.fromJson(codec, json);

		assertEquals(problem.type(), parsed.type());
		assertEquals(problem.title(), parsed.title());
		assertEquals(problem.status(), parsed.status());
		assertEquals(problem.detail(), parsed.detail());
	}
}
