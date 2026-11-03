package org.openindustries.telecom.notification;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class NotificationService {

    private static final Logger LOG = Logger.getLogger(NotificationService.class);

    @Inject
    NotificationRepository repository;

    public Notification dispatch(SendNotificationRequest request) {
        Notification notification = repository.save(
                Notification.create(request.channel(), request.recipient(), request.template()));
        LOG.infof("Dispatching notification %s via %s (template %s)",
                notification.id, notification.channel, notification.template);
        notification.status = "SENT";
        return repository.save(notification);
    }

    public Notification get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "Notification not found: " + id));
    }

    public List<Notification> list() {
        return repository.findAll();
    }
}
