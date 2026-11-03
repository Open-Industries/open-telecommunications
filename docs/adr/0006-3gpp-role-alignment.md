# ADR-0006: 3GPP role alignment (4G and 5G) as the domain organizing principle

| Field | Value |
|---|---|
| Status | Accepted |
| Date | 2026-11-03 |
| Standard traceability | ISO/IEC/IEEE 42010 (architecture decision record) |

## Context

Operators live in a multi-generation world: EPC and SBA run side by side for years. Organizing by role lets one codebase serve both and gives network engineers a familiar map.

## Decision

Bounded contexts align one-to-one with 3GPP roles — HSS→UDM (subscriber), PCRF→PCF (policy), OCS→CHF (charging), P-GW-C→SMF (session), plus NSSF/slice management and NEF (exposure) — per the normative mapping in docs/telecom/3gpp-architecture.md (CR-5).

## Consequences

A shared ubiquitous language with network engineering. The platform models IT-plane roles and contracts; certified protocol stacks stay with network elements (AD §8).
