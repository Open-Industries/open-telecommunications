package org.openindustries.telecom.charging;

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

@Path("/api/v1/charging/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Charging", description = "Convergent charging (CHF-aligned)")
public class ChargingResource {

    @Inject
    ChargingService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Open a prepaid charging account")
    public Response open(@Valid OpenChargingAccountRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.open(request)).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one charging account")
    public ChargingAccount get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST
    @Path("/{id}/usage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Rate and charge one usage event (rejects if it would overdraw the balance)")
    public UsageRecord charge(@PathParam("id") UUID id, @Valid UsageChargeRequest request) {
        return service.charge(id, request);
    }

    @GET
    @Path("/{id}/usage")
    @Operation(summary = "List charged usage records of an account")
    public List<UsageRecord> usage(@PathParam("id") UUID id) {
        return service.usageOf(id);
    }
}
