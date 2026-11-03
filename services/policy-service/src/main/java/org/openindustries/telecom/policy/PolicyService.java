package org.openindustries.telecom.policy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PolicyService {

    @Inject
    PolicyRepository repository;

    public PolicyRule create(CreatePolicyRequest request) {
        return repository.save(PolicyRule.create(request.name(), request.sliceType(),
                request.maxBitrateMbps(), request.latencyBudgetMs(), request.priority()));
    }

    public PolicyRule get(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new DomainException(404, "Policy rule not found: " + id));
    }

    public List<PolicyRule> list() {
        return repository.findAll();
    }

    public PolicyRule activate(UUID id) {
        PolicyRule rule = get(id);
        rule.status = "ACTIVE";
        return repository.save(rule);
    }

    public PolicyEvaluation evaluate(EvaluatePolicyRequest request) {
        var candidates = repository.activeFor(request.sliceType());
        if (candidates.isEmpty()) {
            return new PolicyEvaluation("DENIED", null,
                    "No active policy rule for slice type " + request.sliceType());
        }
        for (PolicyRule rule : candidates) {
            if (rule.latencyBudgetMs <= request.requiredLatencyMs()
                    && rule.maxBitrateMbps >= request.requiredBitrateMbps()) {
                return new PolicyEvaluation("ALLOWED", rule.id,
                        "Rule '" + rule.name + "' covers the requested QoS");
            }
        }
        return new PolicyEvaluation("DEGRADED", candidates.get(0).id,
                "Active rules exist for " + request.sliceType() + " but none meets the requested QoS");
    }
}
