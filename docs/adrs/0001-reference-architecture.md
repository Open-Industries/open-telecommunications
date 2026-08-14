# ADR 0001: Provider-neutral reference architecture

- Status: Accepted
- Date: 2026-08-13

## Decision

Use explicit synchronous and asynchronous contracts, shared ecosystem services, OpenTelemetry and policy as code. Prefer events for variable-load or long-running flows and Saga for multi-domain compensation.

## Consequences

The design improves portability, auditability and selective scaling while increasing contract, telemetry and operational-governance requirements.
