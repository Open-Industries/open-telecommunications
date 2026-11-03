package org.openindustries.telecom.charging;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record UsageChargeRequest(
        @NotBlank @Pattern(regexp = "DATA|VOICE|SMS") String serviceType,
        @Positive long units) {
}
