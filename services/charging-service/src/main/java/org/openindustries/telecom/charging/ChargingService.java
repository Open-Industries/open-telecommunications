package org.openindustries.telecom.charging;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ChargingService {

    @Inject
    ChargingRepository repository;

    public ChargingAccount open(OpenChargingAccountRequest request) {
        return repository.saveAccount(ChargingAccount.open(
                request.subscriberId(), request.currency(), request.initialBalanceMinor()));
    }

    public ChargingAccount get(UUID id) {
        return repository.findAccount(id)
                .orElseThrow(() -> new DomainException(404, "Charging account not found: " + id));
    }

    /**
     * Rates and charges one usage event. Invariant: a prepaid balance never goes
     * negative — usage that exceeds the balance is rejected (422) and nothing is
     * recorded, mirroring CHF quota denial semantics.
     */
    public UsageRecord charge(UUID accountId, UsageChargeRequest request) {
        ChargingAccount account = get(accountId);
        if (!"ACTIVE".equals(account.status)) {
            throw new DomainException(409, "Charging account is not ACTIVE");
        }
        long rate = Tariff.rateFor(request.serviceType());
        long charge = request.units() * rate;
        if (charge > account.balanceMinor) {
            throw new DomainException(422, "Insufficient balance: charge " + charge
                    + " exceeds balance " + account.balanceMinor);
        }
        account.balanceMinor -= charge;
        repository.saveAccount(account);
        return repository.saveRecord(UsageRecord.of(accountId, request.serviceType(),
                request.units(), rate, account.balanceMinor));
    }

    public List<UsageRecord> usageOf(UUID accountId) {
        get(accountId);
        return repository.recordsOf(accountId);
    }
}
