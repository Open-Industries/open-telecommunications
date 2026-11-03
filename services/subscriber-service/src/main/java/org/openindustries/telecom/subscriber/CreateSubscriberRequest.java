package org.openindustries.telecom.subscriber;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateSubscriberRequest(
        @NotBlank @Pattern(regexp = "\\d{7,15}") String msisdn,
        @NotBlank @Pattern(regexp = "\\d{15}") String imsi,
        @NotBlank String fullName,
        @NotBlank @Pattern(regexp = "PREPAID|POSTPAID|ENTERPRISE") String plan,
        @NotBlank @Pattern(regexp = "\\d{5,6}") String homeNetwork) {
}
