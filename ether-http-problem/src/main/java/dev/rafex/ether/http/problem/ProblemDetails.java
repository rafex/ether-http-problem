package dev.rafex.ether.http.problem;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

public record ProblemDetails(
		URI type,
		String title,
		int status,
		String detail,
		URI instance,
		Map<String, Object> properties) {

	public ProblemDetails {
		type = type == null ? URI.create("about:blank") : type;
		title = title == null || title.isBlank() ? "Unknown problem" : title;
		detail = detail == null ? "" : detail;
		properties = properties == null ? Map.of() : Map.copyOf(new LinkedHashMap<>(properties));
	}

	public static Builder builder() {
		return new Builder();
	}

	public static ProblemDetails of(final int status, final String title, final String detail) {
		return builder().status(status).title(title).detail(detail).build();
	}

	public static final class Builder {
		private URI type = URI.create("about:blank");
		private String title;
		private int status;
		private String detail = "";
		private URI instance;
		private final Map<String, Object> properties = new LinkedHashMap<>();

		public Builder type(final URI type) {
			this.type = type;
			return this;
		}

		public Builder title(final String title) {
			this.title = title;
			return this;
		}

		public Builder status(final int status) {
			this.status = status;
			return this;
		}

		public Builder detail(final String detail) {
			this.detail = detail;
			return this;
		}

		public Builder instance(final URI instance) {
			this.instance = instance;
			return this;
		}

		public Builder property(final String key, final Object value) {
			this.properties.put(key, value);
			return this;
		}

		public ProblemDetails build() {
			return new ProblemDetails(type, title, status, detail, instance, properties);
		}
	}
}
