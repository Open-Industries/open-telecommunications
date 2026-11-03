package org.openindustries.telecom.charging;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/** Outbound port; default in-memory adapter, production adapter is JPA on this service's own database. */
@ApplicationScoped
public class ChargingRepository {
    private final ConcurrentHashMap<UUID, ChargingAccount> accounts = new ConcurrentHashMap<>();
    private final CopyOnWriteArrayList<UsageRecord> records = new CopyOnWriteArrayList<>();

    public ChargingAccount saveAccount(ChargingAccount account) {
        accounts.put(account.id, account);
        return account;
    }

    public Optional<ChargingAccount> findAccount(UUID id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public UsageRecord saveRecord(UsageRecord record) {
        records.add(record);
        return record;
    }

    public List<UsageRecord> recordsOf(UUID accountId) {
        return records.stream().filter(r -> r.accountId.equals(accountId)).toList();
    }
}
