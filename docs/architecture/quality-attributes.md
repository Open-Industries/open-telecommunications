# Product Quality Model — ISO/IEC 25010 (SQuaRE)

The **ISO/IEC 25010** product quality model applied to the platform (view VP-9), with
measurable scenarios. Telecom-specific performance targets are expressed per slice class,
because a URLLC slice and an mMTC slice do not share a quality profile.

## 1. Characteristics — commitments

| 25010 characteristic | Platform commitment |
|---|---|
| **Functional suitability** | Charging never overdraws; policy decisions always explain themselves; slice identifiers always derived, never hand-typed downstream |
| **Performance efficiency** | Control-plane APIs: p95 < 300 ms reads, < 800 ms session establishment (reference sizing); per-slice latency budgets are policy data (URLLC reference budget 10 ms at the platform API layer) |
| **Compatibility** | 4G and 5G roles served by the same contexts; OpenAPI/AsyncAPI contracts; CAMARA-style exposure shapes |
| **Usability** *(APIs as UI)* | One error model (RFC 9457), one idempotency convention, decisions with human-readable reasons |
| **Reliability** | SLO 99.9% monthly per service (`../operations/slos.md`); idempotent retries; health probes |
| **Security** | ISO 27002-mapped controls (`../security/`); permanent identifiers masked end-to-end |
| **Maintainability** | One context per service; identical hexagonal layout; ADRs; tests per module |
| **Portability** | Twelve-Factor compliance; same images in central cloud and MEC edge |

## 2. Quality-attribute scenarios

| ID | Characteristic | Scenario | Target |
|---|---|---|---|
| QS-01 | Functional correctness | Usage charge exceeds a prepaid balance | Rejected `422`; balance and records unchanged (tested) |
| QS-02 | Security / privacy | Any API response or log line is inspected for a permanent identifier | Only the masked form ever appears (tested at creation) |
| QS-03 | Performance | Session establishment under reference load (2 replicas, 500 mCPU) | p95 < 800 ms including the synchronous policy evaluation |
| QS-04 | Reliability | A client retries session establishment with the same idempotency key after a timeout | Exactly one session exists (API standard rule) |
| QS-05 | Modifiability | A new slice type (e.g. an IMT-2030 scenario) is introduced | New configuration values + policy rules; no service code changes |
| QS-06 | Portability | The anomaly-scorer function is moved from central cloud to a MEC edge site | Same image, placement-only change |
| QS-07 | Functional suitability | A QoS request arrives for a slice type with no active policy | Decision `DENIED` with reason — never a silent default (tested) |
| QS-08 | Auditability | An auditor asks why a session got its QoS | Policy decision trail names rule, priority and reason |
| QS-09 | Testability | Clean-machine clone | `mvn verify` runs the whole suite with no external services |
| QS-10 | Recoverability | Charging-service pod dies mid-charge | The charge is atomic per request: fully applied or fully rejected; no partial balance |
