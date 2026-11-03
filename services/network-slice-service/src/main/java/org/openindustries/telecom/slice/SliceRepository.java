package org.openindustries.telecom.slice;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class SliceRepository {
    private final ConcurrentHashMap<UUID, NetworkSlice> store = new ConcurrentHashMap<>();

    public NetworkSlice save(NetworkSlice slice) {
        store.put(slice.id, slice);
        return slice;
    }

    public Optional<NetworkSlice> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<NetworkSlice> findAll() {
        return List.copyOf(store.values());
    }
}
