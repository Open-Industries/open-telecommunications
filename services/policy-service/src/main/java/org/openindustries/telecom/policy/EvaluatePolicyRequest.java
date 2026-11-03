package org.openindustries.telecom.policy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record EvaluatePolicyRequest(
        @NotBlank @Pattern(regexp = "EMBB|URLLC|MMTC|ISAC") String sliceType,
        @Positive int requiredLatencyMs,
        @Positive long requiredBitrateMbps) {
}
