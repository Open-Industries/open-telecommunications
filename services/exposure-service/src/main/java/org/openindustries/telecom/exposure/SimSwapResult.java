package org.openindustries.telecom.exposure;

/** SIM-swap risk answer, modeled on the CAMARA SIM Swap API: a recent swap is a fraud
 *  signal for account-takeover. Risk: HIGH (<= 3 days), MEDIUM (<= 30 days), LOW (older). */
public record SimSwapResult(String msisdn, boolean swappedRecently, String riskLevel) {
}
