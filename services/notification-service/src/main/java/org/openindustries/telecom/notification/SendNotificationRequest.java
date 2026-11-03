package org.openindustries.telecom.notification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record SendNotificationRequest(
        @NotBlank @Pattern(regexp = "EMAIL|SMS|PUSH") String channel,
        @NotBlank String recipient,
        @NotBlank String template) {
}
