# ADR-0005: Permanent identifiers are masked; all reference data is synthetic

| Field | Value |
|---|---|
| Status | Accepted |
| Date | 2026-11-03 |
| Standard traceability | ISO/IEC/IEEE 42010 (architecture decision record) |

## Context

Permanent identifiers are among the most sensitive data a telco holds; 5G introduced SUCI concealment precisely to protect them. An IT platform must never undo that protection. ISO 27002 A.5.34.

## Decision

The permanent identifier (IMSI/SUPI) is validated at onboarding and never persisted or returned in clear; only a masked form is kept. Internal keys are UUIDs. Every identifier and record in this repository is synthetic.

## Consequences

Privacy is enforced by construction and covered by tests. Correlation with network-element records happens through the adopter's governed processes, not this API.
