# ADR-0008: API-first contracts with RFC 9457 problem details and CloudEvents

| Field | Value |
|---|---|
| Status | Accepted |
| Date | 2026-11-03 |
| Standard traceability | ISO/IEC/IEEE 42010 (architecture decision record) |

## Context

Contracts are the compatibility promise to BSS, channels and CAMARA consumers, and the testable surface of each service (ISO 25010 interoperability).

## Decision

Public APIs are described by OpenAPI 3 (generated from code, diffed in CI); events by AsyncAPI 2 with the CloudEvents 1.0 envelope; all errors are RFC 9457 problem details.

## Consequences

Generated clients and contract tests; changes require the versioning discipline of the API standard.
