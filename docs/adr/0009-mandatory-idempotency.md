# ADR-0009: Idempotency is mandatory on state-creating operations

| Field | Value |
|---|---|
| Status | Accepted |
| Date | 2026-11-03 |
| Standard traceability | ISO/IEC/IEEE 42010 (architecture decision record) |

## Context

Networks and clients retry — in telecom a duplicate session or double charge is a billing dispute. QS-04 depends on this.

## Decision

Creates accept (and session/charging creates require) an Idempotency-Key; event consumers are idempotent. Retries are always safe.

## Consequences

Safe retries end to end; services store keys with bounded retention (data architecture).
