package org.openindustries.telecom.charging;

import java.util.Map;

/**
 * Reference tariff catalogue — minor currency units per usage unit.
 * DATA is rated per MB, VOICE per minute, SMS per message. Tariffs are configuration
 * data in an adopting operator (TM Forum product catalogue); they live in code here
 * only as the shared reference values, identical to the usage-rating function.
 */
public final class Tariff {
    public static final Map<String, Long> RATE_PER_UNIT_MINOR = Map.of(
            "DATA", 2L,
            "VOICE", 15L,
            "SMS", 5L);

    private Tariff() {
    }

    public static long rateFor(String serviceType) {
        Long rate = RATE_PER_UNIT_MINOR.get(serviceType);
        if (rate == null) {
            throw new DomainException(422, "No tariff for service type " + serviceType);
        }
        return rate;
    }
}
