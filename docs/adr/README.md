# Architecture Decision Records (ADRs)

Part of the Architecture Description (ISO/IEC/IEEE 42010 §5.6). Accepted ADRs are
immutable; a change is a new ADR that supersedes the old one.

| ADR | Title |
|---|---|
| [0001](0001-microservices-and-serverless.md) | Microservices per bounded context, complemented by serverless functions |
| [0002](0002-java-quarkus-stack.md) | Java 21 and Quarkus as the single implementation stack |
| [0003](0003-database-per-service.md) | Database per service; no shared database, no distributed transactions |
| [0004](0004-charging-integrity.md) | Charging integrity: prepaid balances never go negative |
| [0005](0005-identifiers-privacy.md) | Permanent identifiers are masked; all reference data is synthetic |
| [0006](0006-3gpp-role-alignment.md) | 3GPP role alignment (4G and 5G) as the domain organizing principle |
| [0007](0007-camara-exposure.md) | Network exposure follows GSMA Open Gateway / CAMARA API shapes |
| [0008](0008-api-first-contracts.md) | API-first contracts with RFC 9457 problem details and CloudEvents |
| [0009](0009-mandatory-idempotency.md) | Idempotency is mandatory on state-creating operations |
| [0010](0010-security-model.md) | Security model: OIDC at the edge, mTLS inside, platform-managed secrets |
| [0011](0011-twelve-factor-binding.md) | Twelve-Factor alignment as a binding application standard |
| [0012](0012-6g-framework-alignment.md) | 6G readiness by IMT-2030 framework alignment, not by speculation |
