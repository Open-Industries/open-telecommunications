# ADR-0007: Network exposure follows GSMA Open Gateway / CAMARA API shapes

| Field | Value |
|---|---|
| Status | Accepted |
| Date | 2026-11-03 |
| Standard traceability | ISO/IEC/IEEE 42010 (architecture decision record) |

## Context

CAMARA is the industry-agreed exposure surface; applications written against it port across operators. Deviating would isolate adopters.

## Decision

Developer-facing network APIs (Quality on Demand, SIM Swap, and future additions) follow the CAMARA API style and semantics, realized over the NEF role.

## Consequences

Consumers get portable contracts. CAMARA evolution is tracked as contract changes under the API standard's versioning rules.
