package org.openindustries.telecom.notification;

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

@Path("/api/v1/notifications")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Notifications", description = "Subscriber and network notifications")
public class NotificationResource {

    @Inject
    NotificationService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Dispatch a notification")
    public Response dispatch(@Valid SendNotificationRequest request) {
        return Response.status(Response.Status.ACCEPTED).entity(service.dispatch(request)).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get one notification")
    public Notification get(@PathParam("id") UUID id) {
        return service.get(id);
    }

    @GET
    @Operation(summary = "List notifications")
    public List<Notification> list() {
        return service.list();
    }
}
