package org.openindustries.telecom.session;

import jakarta.ws.rs.core.Response;

/** Domain rule violation. Mapped to an RFC 9457 problem response. */
public class DomainException extends RuntimeException {
    private final int status;

    public DomainException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int status() {
        return status;
    }

    public Response toResponse() {
        return Problem.of(status, "Domain rule violation", getMessage()).toResponse();
    }
}
