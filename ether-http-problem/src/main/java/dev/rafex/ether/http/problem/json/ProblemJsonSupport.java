package dev.rafex.ether.http.problem.json;

import dev.rafex.ether.http.problem.model.ProblemDetails;
import dev.rafex.ether.json.JsonCodec;

public final class ProblemJsonSupport {

    private ProblemJsonSupport() {
    }

    public static String toJson(final JsonCodec jsonCodec, final ProblemDetails problem) {
        return jsonCodec.toJson(problem);
    }

    public static ProblemDetails fromJson(final JsonCodec jsonCodec, final String json) {
        return jsonCodec.readValue(json, ProblemDetails.class);
    }
}
