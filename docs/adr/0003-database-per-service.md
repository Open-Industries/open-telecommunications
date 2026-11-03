# ADR-0003: Database per service; no shared database, no distributed transactions

| Field | Value |
|---|---|
| Status | Accepted |
| Date | 2026-11-03 |
| Standard traceability | ISO/IEC/IEEE 42010 (architecture decision record) |

## Context

A shared database is the strongest coupling in operator IT and blocks independent evolution. CR-4 makes cross-context database access an architecture violation.

## Decision

Each microservice owns its schema and credentials. Cross-service consistency uses domain events and explicit compensation, never XA/2PC or shared tables.

## Consequences

Autonomy and least-privilege data access. Reporting is built from events, not joins.
