package org.openindustries.telecom.session;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class SessionRepository {
    private final ConcurrentHashMap<UUID, PduSession> store = new ConcurrentHashMap<>();

    public PduSession save(PduSession session) {
        store.put(session.id, session);
        return session;
    }

    public Optional<PduSession> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<PduSession> findBySubscriber(UUID subscriberId) {
        return store.values().stream().filter(s -> s.subscriberId.equals(subscriberId)).toList();
    }
}
