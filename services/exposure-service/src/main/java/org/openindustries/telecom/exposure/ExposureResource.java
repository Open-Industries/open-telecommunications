package org.openindustries.telecom.exposure;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.UUID;

@Path("/api/v1/exposure")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Network Exposure", description = "CAMARA / Open Gateway-style network APIs (NEF-aligned)")
public class ExposureResource {

    @Inject
    ExposureService service;

    @POST
    @Path("/qod/sessions")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a Quality-on-Demand session (CAMARA QoD)")
    public Response createQod(@Valid CreateQodRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.createQod(request)).build();
    }

    @GET
    @Path("/qod/sessions/{id}")
    @Operation(summary = "Get one QoD session")
    public QodSession getQod(@PathParam("id") UUID id) {
        return service.getQod(id);
    }

    @POST
    @Path("/sim-swap/check")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Check SIM-swap recency and risk (CAMARA SIM Swap)")
    public SimSwapResult checkSimSwap(@Valid SimSwapCheckRequest request) {
        return service.checkSimSwap(request);
    }
}
