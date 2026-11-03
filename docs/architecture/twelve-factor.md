# Twelve-Factor Alignment

Binding application standard (ADR-0011). Each factor of
[The Twelve-Factor App](https://12factor.net) mapped to its implementation and evidence —
identical discipline to the Open Industries reference cores.

| # | Factor | Implementation here | Evidence |
|---|---|---|---|
| I | Codebase | One repo, one Maven reactor; services + functions deploy independently | Root `pom.xml` |
| II | Dependencies | Declared, BOM-managed (Quarkus); containers carry their runtime | `pom.xml` files; Dockerfiles |
| III | Config | Environment variables only (`HTTP_PORT`, `DB_URL`, `OIDC_ISSUER`, `LOG_LEVEL`); secrets from the platform store, never committed | `application.properties` `${ENV:default}` placeholders; `.env.example` names only |
| IV | Backing services | Databases, broker, providers are attached resources, swapped by config (tariff/provider adapters) | Repository ports; `deploy/` |
| V | Build, release, run | Immutable images built once, promoted across environments | CI workflow; overlays |
| VI | Processes | Stateless services; functions are pure input→output | No session state in-JVM |
| VII | Port binding | Embedded HTTP per service (8081–8087; functions 8080) | `quarkus.http.port` |
| VIII | Concurrency | Replicas + HPA; Knative scaling for functions | `deploy/kubernetes/`, `deploy/knative/` |
| IX | Disposability | Fast Quarkus startup, graceful shutdown, idempotent consumers | Probes in manifests |
| X | Dev/prod parity | Same images/topology everywhere incl. MEC edge; local deps via compose | `docker-compose.yml` |
| XI | Logs | Structured event streams to stdout; platform aggregates | `quarkus.log` config |
| XII | Admin processes | Migrations, tariff loads and SLA re-evaluations as one-off Jobs/functions on the same image/config | `../operations/runbook.md` |

Deviations require an ADR superseding ADR-0011.
