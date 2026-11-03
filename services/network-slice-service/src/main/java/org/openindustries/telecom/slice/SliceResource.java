package org.openindustries.telecom.slice;

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

import java.util.List;
import java.util.UUID;

@Path("/api/v1/slices")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Network Slices", description = "Slice lifecycle and S-NSSAI registry (TS 28.531-aligned)")
public class SliceResource {

    @Inject
    SliceService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Provision a network slice")
    public Response create(@Valid CreateSliceRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.create(request)).build();
    }

    @GET
    @Operation(summary = "List network slices")
    public List<NetworkSlice> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one network slice")
    public NetworkSlice get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST
    @Path("/{id}/activate")
    @Operation(summary = "Activate a provisioned slice")
    public NetworkSlice activate(@PathParam("id") UUID id) {
        return service.activate(id);
    }

    @POST
    @Path("/{id}/decommission")
    @Operation(summary = "Decommission a slice")
    public NetworkSlice decommission(@PathParam("id") UUID id) {
        return service.decommission(id);
    }
}
