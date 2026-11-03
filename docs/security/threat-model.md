# Threat Model (STRIDE summary)

Scope: the platform boundary in `security-architecture.md`. Reviewed at every architecture change.

| Threat | Telecom-flavored example | Mitigation in place |
|---|---|---|
| **S**poofing | Forged CAMARA application credentials calling exposure APIs | Gateway OIDC + per-application credentials and consent; mTLS inside |
| **T**ampering | Altering a usage record to erase charges | Charging records are append-only evidence; balance changes only through the charging operation; reconciliation against rated events |
| **R**epudiation | Operator denies a policy change that degraded a slice | Audit log (actor, timestamp, correlation id) on every state change; policy decisions carry rule + reason |
| **I**nformation disclosure | Permanent identifiers or usage history leaking via API/log | Masked IMSI by construction (tested); usage data minimized; recipients redacted in logs; RFC 9457 without internals |
| **D**enial of service | Signaling-adjacent event flood or API abuse | Gateway rate limiting, HPA, Knative concurrency limits, per-service quotas; signaling planes are out of the platform's trust zone entirely |
| **E**levation of privilege | A compromised notification service reading charging data | Database-per-service credentials: its credentials open only its own schema |

SIM-swap account takeover is treated as a *consumer* threat the platform helps detect
(exposure risk answers), not one it can prevent alone. Out of scope: SS7/Diameter
signaling security of network elements, RAN air-interface security, lawful intercept.
