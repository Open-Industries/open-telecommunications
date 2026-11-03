package org.openindustries.telecom.policy;

import java.util.UUID;

/**
 * Policy rule aggregate — aligned with the 5G PCF policy model (TS 23.501) and the 4G
 * PCRF before it: a rule binds a slice type to QoS guarantees (bitrate ceiling and
 * latency budget) with a precedence priority.
 */
public class PolicyRule {
    public UUID id;
    public String name;
    public String sliceType;        // EMBB | URLLC | MMTC | ISAC
    public long maxBitrateMbps;
    public int latencyBudgetMs;
    public int priority;            // 1 (highest) .. 100
    public String status;           // DRAFT | ACTIVE

    public static PolicyRule create(String name, String sliceType, long maxBitrateMbps, int latencyBudgetMs, int priority) {
        PolicyRule r = new PolicyRule();
        r.id = UUID.randomUUID();
        r.name = name;
        r.sliceType = sliceType;
        r.maxBitrateMbps = maxBitrateMbps;
        r.latencyBudgetMs = latencyBudgetMs;
        r.priority = priority;
        r.status = "DRAFT";
        return r;
    }
}
