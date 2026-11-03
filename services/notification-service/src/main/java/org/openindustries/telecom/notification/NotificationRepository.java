package org.openindustries.telecom.notification;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class NotificationRepository {
    private final ConcurrentHashMap<UUID, Notification> store = new ConcurrentHashMap<>();

    public Notification save(Notification notification) {
        store.put(notification.id, notification);
        return notification;
    }

    public Optional<Notification> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Notification> findAll() {
        return List.copyOf(store.values());
    }
}
