package org.openindustries.telecom.policy;

import java.util.UUID;

/** Policy decision: ALLOWED (an active rule fully covers the request), DEGRADED (a rule
 *  exists for the slice type but cannot meet the requirement), DENIED (no active rule). */
public record PolicyEvaluation(String decision, UUID matchedRuleId, String reason) {
}
