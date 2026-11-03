# ADR-0001: Microservices per bounded context, complemented by serverless functions

| Field | Value |
|---|---|
| Status | Accepted |
| Date | 2026-11-03 |
| Standard traceability | ISO/IEC/IEEE 42010 (architecture decision record) |

## Context

Capabilities change at different rates and carry different risk (a tariff change must not redeploy session control). Functions remove idle cost for event-driven assurance work. One stack serves both styles.

## Decision

Core capabilities are independently deployable Quarkus microservices, one per bounded context; bursty/assurance work (rating, anomaly scoring, SLA evaluation) is Quarkus Funqy functions on Knative with scale-to-zero.

## Consequences

Independent scaling and releases; zero-cost idle functions. Cost: distributed-systems complexity, mitigated by contracts, idempotency and observability standards.
