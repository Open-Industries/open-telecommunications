package org.openindustries.telecom.subscriber;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Outbound port (hexagonal architecture). Default adapter is in-memory so the reference
 * implementation runs anywhere; the production adapter is JPA against this service's own
 * database — the UDR role of the 5G architecture (database-per-service, ADR-0003).
 */
@ApplicationScoped
public class SubscriberRepository {
    private final ConcurrentHashMap<UUID, Subscriber> store = new ConcurrentHashMap<>();

    public Subscriber save(Subscriber subscriber) {
        store.put(subscriber.id, subscriber);
        return subscriber;
    }

    public Optional<Subscriber> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Subscriber> findAll() {
        return List.copyOf(store.values());
    }
}
