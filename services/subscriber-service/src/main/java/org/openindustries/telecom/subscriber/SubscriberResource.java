package org.openindustries.telecom.subscriber;

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

@Path("/api/v1/subscribers")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Subscribers", description = "Subscription profiles (UDM/HSS-aligned)")
public class SubscriberResource {

    @Inject
    SubscriberService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Onboard a subscriber (permanent identifier is masked, never stored in clear)")
    public Response onboard(@Valid CreateSubscriberRequest request) {
        return Response.status(Response.Status.CREATED).entity(service.onboard(request)).build();
    }

    @GET
    @Operation(summary = "List subscribers")
    public List<Subscriber> list() {
        return service.list();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one subscriber")
    public Subscriber get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST
    @Path("/{id}/suspend")
    @Operation(summary = "Suspend a subscription")
    public Subscriber suspend(@PathParam("id") UUID id) {
        return service.suspend(id);
    }

    @POST
    @Path("/{id}/reactivate")
    @Operation(summary = "Reactivate a suspended subscription")
    public Subscriber reactivate(@PathParam("id") UUID id) {
        return service.reactivate(id);
    }
}
