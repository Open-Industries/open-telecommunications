package org.openindustries.telecom.exposure;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class ExposureRepository {
    private final ConcurrentHashMap<UUID, QodSession> store = new ConcurrentHashMap<>();

    public QodSession save(QodSession session) {
        store.put(session.id, session);
        return session;
    }

    public Optional<QodSession> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }
}
