# ADR-0011: Twelve-Factor alignment as a binding application standard

| Field | Value |
|---|---|
| Status | Accepted |
| Date | 2026-11-03 |
| Standard traceability | ISO/IEC/IEEE 42010 (architecture decision record) |

## Context

A generic, deployable core is only generic if any adopter can build, release and run it — centrally or at a MEC edge — without modification (ISO 25010 portability).

## Decision

The Twelve-Factor methodology is adopted in full; docs/architecture/twelve-factor.md maps each factor to implementation and evidence. Deviations require a superseding ADR.

## Consequences

Portable and operable everywhere. Local state, file logs and environment-specific builds are forbidden by design.
