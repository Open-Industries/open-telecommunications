package org.openindustries.telecom.session;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/v1/sessions")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "PDU Sessions", description = "Session lifecycle (SMF-aligned)")
public class SessionResource {

    @Inject
    SessionService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Establish a PDU session")
    public Response establish(@Valid CreateSessionRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.establish(request)).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one PDU session")
    public PduSession get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @GET
    @Operation(summary = "List PDU sessions of a subscriber")
    public List<PduSession> bySubscriber(@QueryParam("subscriberId") UUID subscriberId) {
        return service.bySubscriber(subscriberId);
    }

    @POST
    @Path("/{id}/release")
    @Operation(summary = "Release a PDU session")
    public PduSession release(@PathParam("id") UUID id) {
        return service.release(id);
    }
}
