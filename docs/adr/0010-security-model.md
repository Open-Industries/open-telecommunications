# ADR-0010: Security model: OIDC at the edge, mTLS inside, platform-managed secrets

| Field | Value |
|---|---|
| Status | Accepted |
| Date | 2026-11-03 |
| Standard traceability | ISO/IEC/IEEE 42010 (architecture decision record) |

## Context

Implements the ISO 27001/27002 mapping with the smallest code footprint: no custom cryptography, no secrets in the repository, signaling planes outside the trust zone.

## Decision

The gateway validates OIDC/OAuth 2.1 tokens; service traffic is mTLS; per-service least-privilege credentials; secrets exist only in the platform store, injected as environment variables.

## Consequences

Auditable, provider-swappable identity. Local development uses the compose-provided IdP.
