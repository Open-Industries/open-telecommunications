package org.openindustries.telecom.session;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record CreateSessionRequest(
        @NotNull UUID subscriberId,
        @NotBlank @Pattern(regexp = "\\d{1,3}-[0-9A-Fa-f]{6}") String snssai,
        @NotBlank @Pattern(regexp = "internet|ims|iot") String dataNetworkName,
        @NotBlank @Pattern(regexp = "EMBB|URLLC|MMTC") String qosClass) {
}
