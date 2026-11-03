# ISO/IEC 27001 & 27002 — Control Mapping

Technical implementation of ISO/IEC 27002:2022 controls relevant to this platform, as
evidence for an adopter-run ISO/IEC 27001 ISMS. Organization-level duties (risk
assessment, Statement of Applicability, internal audit) remain the adopter's.

| ISO 27002 control | Implementation here |
|---|---|
| 5.1 Security policies | This `docs/security/` set; security rules are binding (ADR-0010) |
| 5.7 Threat intelligence | CI dependency/image scanning; base images rebuilt on CVE feeds |
| 5.15 Access control | OIDC at the edge, mTLS workload identity, per-service least-privilege DB credentials, deny-by-default network policies |
| 5.17 Authentication information | Platform secret store only; env injection; **no secrets in git** (CI scanning) |
| 5.19–5.22 Supplier relationships | IdP, notification providers and network-element integrations sit behind adapters with monitored SLOs |
| 5.24–5.28 Incident management | Runbook (`../operations/runbook.md`); immutable logs for forensics |
| 5.31 Legal & regulatory requirements | Traceability matrix (`../compliance/standards-traceability.md`); retention rules in the data architecture; LI/emergency functions explicitly out of scope (certified elements) |
| 5.34 Privacy and PII | Masked permanent identifiers; usage-data minimization and retention; redacted logs |
| 5.35 Independent review | ADR process + this document set as auditor evidence |
| 6.x People controls | Adopter ISMS duty; technically supported by SSO+MFA and least privilege |
| 7.x Physical controls | **Inherited** from the hosting platform/data centers (incl. MEC edge sites) |
| 8.2 Privileged access | No human write to production subscriber/charging data; break-glass is platform-audited |
| 8.3 Information access restriction | Database-per-service isolation (CR-4) |
| 8.5 Secure authentication | OAuth 2.1 + PKCE; mTLS between services |
| 8.9 Configuration management | Everything versioned in git (code, contracts, manifests, docs) |
| 8.13 Backup | Per-service backups; restore tested as an admin process |
| 8.15 Logging | Structured stdout logs, central aggregation, correlation ids; charging records are an append-only financial trail |
| 8.16 Monitoring | Prometheus metrics, health probes, SLO alerts; anomaly scoring for network KPIs |
| 8.24 Cryptography | TLS 1.2+ in transit, platform encryption at rest, no custom crypto |
| 8.25–8.29 Secure development | Gated life cycle (`../lifecycle/software-lifecycle.md`): contract tests, scans, peer review, signed images |
| 8.31 Environment separation | Identical topology per environment; production data never enters lower environments |
| 8.34 Audit testing protection | Evidence produced from logs/ADRs/CI records — no auditor access to live subscriber data |
