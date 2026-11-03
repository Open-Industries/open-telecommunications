package org.openindustries.telecom.policy;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class PolicyRepository {
    private final ConcurrentHashMap<UUID, PolicyRule> store = new ConcurrentHashMap<>();

    public PolicyRule save(PolicyRule rule) {
        store.put(rule.id, rule);
        return rule;
    }

    public Optional<PolicyRule> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<PolicyRule> findAll() {
        return List.copyOf(store.values());
    }

    /** Active rules for a slice type, highest priority (lowest number) first. */
    public List<PolicyRule> activeFor(String sliceType) {
        return store.values().stream()
                .filter(r -> r.sliceType.equals(sliceType) && "ACTIVE".equals(r.status))
                .sorted(Comparator.comparingInt(r -> r.priority))
                .toList();
    }
}
