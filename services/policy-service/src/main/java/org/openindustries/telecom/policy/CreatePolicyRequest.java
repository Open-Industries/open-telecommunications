package org.openindustries.telecom.policy;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreatePolicyRequest(
        @NotBlank String name,
        @NotBlank @Pattern(regexp = "EMBB|URLLC|MMTC|ISAC") String sliceType,
        @Positive long maxBitrateMbps,
        @Positive int latencyBudgetMs,
        @Min(1) @Max(100) int priority) {
}
