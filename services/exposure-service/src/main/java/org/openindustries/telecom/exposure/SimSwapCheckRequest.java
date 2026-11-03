package org.openindustries.telecom.exposure;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SimSwapCheckRequest(
        @NotBlank @Pattern(regexp = "\\d{7,15}") String msisdn,
        @Min(0) int lastSwapDaysAgo) {
}
