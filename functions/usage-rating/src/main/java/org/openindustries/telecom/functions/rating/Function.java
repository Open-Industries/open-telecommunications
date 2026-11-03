package org.openindustries.telecom.functions.rating;

import io.quarkus.funqy.Funq;

import java.util.Map;

/**
 * Serverless function (Knative / OpenShift Serverless).
 * Rates a raw usage event with the reference tariff catalogue — the same values the
 * Charging Service applies (DATA 2/MB, VOICE 15/min, SMS 5/msg, minor units) — so
 * offline rating and online charging can never drift apart.
 * Stateless by design (12-Factor VI): all state arrives in the request.
 */
public class Function {

    static final Map<String, Long> RATE_PER_UNIT_MINOR = Map.of(
            "DATA", 2L, "VOICE", 15L, "SMS", 5L);

    public record UsageEvent(String subscriberId, String serviceType, long units) {
    }

    public record RatedUsage(String subscriberId, String serviceType, long units,
                             long ratePerUnitMinor, long chargeMinor, String ratingRule) {
    }

    @Funq
    public RatedUsage usageRating(UsageEvent event) {
        Long rate = RATE_PER_UNIT_MINOR.get(event.serviceType());
        if (rate == null) {
            throw new IllegalArgumentException("No tariff for service type " + event.serviceType());
        }
        return new RatedUsage(event.subscriberId(), event.serviceType(), event.units(),
                rate, event.units() * rate, "reference-tariff-v1");
    }
}
