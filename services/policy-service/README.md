# Policy Service

Bounded context: **Policy** — QoS/policy rules aligned with the 5G **PCF** role
(TS 23.501) and the 4G PCRF. Decisions: `ALLOWED` / `DEGRADED` / `DENIED`, always with the
matched rule and a human-readable reason for audit.

| Item | Value |
|---|---|
| Port | `8083` (`HTTP_PORT`) |
| Base path | `/api/v1/policies` |
| Owns data | Policy rules |
| Publishes | `policy.activated` |
