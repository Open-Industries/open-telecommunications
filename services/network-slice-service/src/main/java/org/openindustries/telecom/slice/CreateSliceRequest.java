package org.openindustries.telecom.slice;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CreateSliceRequest(
        @NotBlank String name,
        @Min(1) @Max(255) int sst,
        @Pattern(regexp = "[0-9A-Fa-f]{6}") String sd,
        @NotBlank @Pattern(regexp = "EMBB|URLLC|MMTC|ISAC") String sliceType,
        @Positive int latencyTargetMs,
        @Positive long maxDevices) {
}
