# ADR-0004: Charging integrity: prepaid balances never go negative

| Field | Value |
|---|---|
| Status | Accepted |
| Date | 2026-11-03 |
| Standard traceability | ISO/IEC/IEEE 42010 (architecture decision record) |

## Context

Charging correctness is the financial trust core of a telco platform, exactly as the ledger is for a bank. QS-01 and QS-10 depend on this decision.

## Decision

Usage is rated against the shared reference tariff and charged atomically per request. A charge that would overdraw the balance is rejected (422) and records nothing — CHF quota-denial semantics.

## Consequences

Provable, testable charging behavior. Callers must handle 422 as a normal business outcome (top-up flows), which the API standard documents.
