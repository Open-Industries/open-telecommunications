package org.openindustries.telecom.exposure;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateQodRequest(
        @NotBlank @Pattern(regexp = "\\d{7,15}") String deviceMsisdn,
        @NotBlank @Pattern(regexp = "LOW_LATENCY|HIGH_THROUGHPUT|DEFAULT") String qosProfile,
        @Min(5) @Max(1440) int durationMinutes) {
}
