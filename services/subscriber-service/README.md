# Subscriber Service

Bounded context: **Subscriber** — subscription profiles, aligned with the 3GPP UDM/UDR
subscription-data role (5G, TS 23.501) and the HSS role in 4G/EPC (TS 23.401).

Privacy by design: the permanent identifier (IMSI/SUPI) is validated at onboarding and
**never persisted or returned in clear** — only a masked form is kept. All identifiers in
this reference implementation are synthetic.

| Item | Value |
|---|---|
| Port | `8081` (`HTTP_PORT`) |
| Base path | `/api/v1/subscribers` |
| Owns data | Subscriber profile, subscription status |
| Publishes | `subscriber.onboarded`, `subscriber.suspended` (CloudEvents, see `contracts/asyncapi.yaml`) |

Run locally: `mvn -pl services/subscriber-service quarkus:dev`.
