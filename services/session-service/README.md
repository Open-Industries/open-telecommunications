# Session Service

Bounded context: **PDU Session** — session lifecycle aligned with the 5G **SMF** role
(TS 23.501/23.502) and the 4G PDN connection (P-GW control). The UPF user plane is a
network element; this service owns session state, slice binding (S-NSSAI) and lifecycle.

| Item | Value |
|---|---|
| Port | `8082` (`HTTP_PORT`) |
| Base path | `/api/v1/sessions` |
| Owns data | PDU sessions |
| Publishes | `session.established`, `session.released` |
