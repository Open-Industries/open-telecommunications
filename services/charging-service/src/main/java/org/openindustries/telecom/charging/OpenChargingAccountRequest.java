package org.openindustries.telecom.charging;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record OpenChargingAccountRequest(
        @NotNull UUID subscriberId,
        @NotBlank @Pattern(regexp = "[A-Z]{3}") String currency,
        @PositiveOrZero long initialBalanceMinor) {
}
