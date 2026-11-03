package org.openindustries.telecom.charging;

import java.time.Instant;
import java.util.UUID;

/** One rated, charged usage event — the convergent-charging record (CDR-equivalent). */
public class UsageRecord {
    public UUID id;
    public UUID accountId;
    public String serviceType;
    public long units;
    public long ratePerUnitMinor;
    public long chargeMinor;
    public long balanceAfterMinor;
    public Instant chargedAt;

    public static UsageRecord of(UUID accountId, String serviceType, long units, long rate, long balanceAfter) {
        UsageRecord r = new UsageRecord();
        r.id = UUID.randomUUID();
        r.accountId = accountId;
        r.serviceType = serviceType;
        r.units = units;
        r.ratePerUnitMinor = rate;
        r.chargeMinor = units * rate;
        r.balanceAfterMinor = balanceAfter;
        r.chargedAt = Instant.now();
        return r;
    }
}
