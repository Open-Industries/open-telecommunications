package org.openindustries.telecom.functions.sla;

import io.quarkus.funqy.Funq;

import java.util.ArrayList;
import java.util.List;

/**
 * Serverless function (Knative / OpenShift Serverless).
 * Compares measured slice KPIs with the SLA targets of a network slice (latency and
 * availability) and returns a verdict with one finding per breach — the evidence feed
 * for slice assurance (3GPP TS 28.53x management) and enterprise SLA reporting.
 */
public class Function {

    public record SliceKpi(String sliceId, int measuredLatencyMs, int targetLatencyMs,
                           double measuredAvailabilityPct, double targetAvailabilityPct) {
    }

    public record SlaVerdict(String sliceId, boolean compliant, List<String> findings) {
    }

    @Funq
    public SlaVerdict slaComplianceEvaluator(SliceKpi kpi) {
        List<String> findings = new ArrayList<>();
        if (kpi.measuredLatencyMs() > kpi.targetLatencyMs()) {
            findings.add("latency " + kpi.measuredLatencyMs() + " ms exceeds target "
                    + kpi.targetLatencyMs() + " ms");
        }
        if (kpi.measuredAvailabilityPct() < kpi.targetAvailabilityPct()) {
            findings.add("availability " + kpi.measuredAvailabilityPct()
                    + "% below target " + kpi.targetAvailabilityPct() + "%");
        }
        return new SlaVerdict(kpi.sliceId(), findings.isEmpty(), findings);
    }
}
