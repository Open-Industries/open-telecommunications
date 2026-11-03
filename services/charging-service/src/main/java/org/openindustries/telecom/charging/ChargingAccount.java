package org.openindustries.telecom.charging;

import java.util.UUID;

/** Prepaid charging account — the balance the CHF role guards. Never goes negative. */
public class ChargingAccount {
    public UUID id;
    public UUID subscriberId;
    public long balanceMinor;
    public String currency;   // ISO 4217
    public String status;     // ACTIVE | SUSPENDED

    public static ChargingAccount open(UUID subscriberId, String currency, long initialBalanceMinor) {
        ChargingAccount a = new ChargingAccount();
        a.id = UUID.randomUUID();
        a.subscriberId = subscriberId;
        a.balanceMinor = initialBalanceMinor;
        a.currency = currency;
        a.status = "ACTIVE";
        return a;
    }
}
