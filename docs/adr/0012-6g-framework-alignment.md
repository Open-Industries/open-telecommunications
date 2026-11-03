# ADR-0012: 6G readiness by IMT-2030 framework alignment, not by speculation

| Field | Value |
|---|---|
| Status | Accepted |
| Date | 2026-11-03 |
| Standard traceability | ISO/IEC/IEEE 42010 (architecture decision record) |

## Context

There is no finished 6G standard: 3GPP work is at study stage. Claiming more would be false; designing extension points now is cheap and honest.

## Decision

6G is addressed exclusively through the ITU-R IMT-2030 framework (M.2160): its usage scenarios are represented as configuration-level extension points (e.g. the ISAC slice type, model-swappable scoring functions). No 6G conformance or compliance is claimed anywhere.

## Consequences

Adopters get a credible migration story. When 3GPP 6G specifications land, changes concentrate in adapters and network elements, per docs/telecom/6g-imt-2030.md.
