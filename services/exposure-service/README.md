# Exposure Service

Bounded context: **Network Exposure** — developer-facing network APIs in the style of
**GSMA Open Gateway / CAMARA**, realized over the 5G **NEF** role (TS 23.501):
Quality-on-Demand sessions and SIM Swap checks. Identifiers are synthetic.

| Item | Value |
|---|---|
| Port | `8086` (`HTTP_PORT`) |
| Base path | `/api/v1/exposure` |
| Owns data | QoD sessions |
| Publishes | `exposure.qod-created` |
