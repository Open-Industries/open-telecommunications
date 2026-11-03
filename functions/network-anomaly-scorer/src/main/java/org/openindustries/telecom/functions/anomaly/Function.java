package org.openindustries.telecom.functions.anomaly;

import io.quarkus.funqy.Funq;

/**
 * Serverless function (Knative / OpenShift Serverless).
 * Scores a cell/KPI event with transparent, auditable rules and recommends
 * NORMAL (< 35), WARNING (35-69) or CRITICAL (>= 70) for the operations loop —
 * a building block towards ETSI ZSM closed-loop automation. The rationale is
 * returned with the score for audit (ISO/IEC 27001 A.8.15 logging).
 */
public class Function {

    public record NetworkKpiEvent(String cellId, double packetLossPct, int latencyMs, int throughputDropPct) {
    }

    public record AnomalyScore(String cellId, int score, String decision, String rationale) {
    }

    @Funq
    public AnomalyScore networkAnomalyScorer(NetworkKpiEvent event) {
        int score = 0;
        StringBuilder why = new StringBuilder();
        if (event.packetLossPct() >= 10.0) {
            score += 40;
            why.append("packet loss >= 10%; ");
        }
        if (event.latencyMs() >= 150) {
            score += 30;
            why.append("latency >= 150 ms; ");
        }
        if (event.throughputDropPct() >= 50) {
            score += 30;
            why.append("throughput drop >= 50%; ");
        }
        String decision = score >= 70 ? "CRITICAL" : score >= 35 ? "WARNING" : "NORMAL";
        return new AnomalyScore(event.cellId(), score, decision,
                why.length() == 0 ? "no signals" : why.toString().trim());
    }
}
