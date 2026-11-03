# Network Slice Service

Bounded context: **Network Slicing** — slice lifecycle (provision → activate →
decommission) and the S-NSSAI registry, aligned with 3GPP network slice management
(TS 28.531) and the slice selection role of the NSSF (TS 23.501). Slice types include
the IMT-2030 forward-looking `ISAC` value alongside eMBB/URLLC/mMTC.

| Item | Value |
|---|---|
| Port | `8085` (`HTTP_PORT`) |
| Base path | `/api/v1/slices` |
| Owns data | Network slices, S-NSSAI registry |
| Publishes | `slice.activated`, `slice.decommissioned` |
