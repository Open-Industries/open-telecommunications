# Services

One independently deployable Quarkus microservice per bounded context, each aligned to a
3GPP role (see `../docs/telecom/3gpp-architecture.md`). Every service follows the same
hexagonal layout, exposes `/openapi`, `/q/health` and `/q/metrics`, returns RFC 9457
problem details, and is configured only through environment variables (Twelve-Factor).
Lifecycle transitions take no body; `@Consumes` appears only on endpoints that read one.

**Implementation order** (see `../docs/implementation-roadmap.md`):
Subscriber → Session → Charging → Open Network APIs (Exposure).

| Service | Port | 3GPP alignment | Step |
|---|---|---|---|
| `subscriber-service` | 8081 | UDM/UDR · HSS | **Step 1 (pilot)** |
| `session-service` | 8082 | SMF · P-GW-C | Step 2 |
| `policy-service` | 8083 | PCF · PCRF | Step 2 |
| `network-slice-service` | 8085 | NSSF · TS 28.531 | Step 2 |
| `charging-service` | 8084 | CHF · OCS | Step 3 |
| `exposure-service` | 8086 | NEF · CAMARA | Step 4 |
| `notification-service` | 8087 | platform support | Step 4 |

Run the pilot service:

```bash
mvn -pl services/subscriber-service quarkus:dev
# OpenAPI UI: http://localhost:8081/docs — health: http://localhost:8081/q/health
```
