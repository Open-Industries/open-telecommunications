package org.openindustries.telecom.charging;

import jakarta.ws.rs.core.Response;

/** RFC 9457 Problem Details — the single error model for every API in the platform. */
public record Problem(String type, String title, int status, String detail) {

    public static Problem of(int status, String title, String detail) {
        return new Problem("about:blank", title, status, detail);
    }

    public Response toResponse() {
        return Response.status(status).type("application/problem+json").entity(this).build();
    }
}
