# 3GPP Architecture Alignment — 4G/EPC and 5G/SBA

How the platform's bounded contexts align with the two standardized core architectures an
operator may run today. This is an **alignment mapping**, not a conformance claim: the
platform realizes the IT-plane roles and contracts of these functions; it does not
implement certified radio or signaling protocol stacks.

## 1. 4G — Evolved Packet Core (TS 23.401)

The EPC separates control and user plane around the MME (mobility), HSS (subscription
data), PCRF (policy), OCS (online charging) and the serving/P-GW pair (session/user plane).

## 2. 5G — Service-Based Architecture (TS 23.501, procedures TS 23.502)

The 5G core decomposes the same responsibilities into network functions communicating over
service-based interfaces: AMF (access/mobility), SMF (session), UPF (user plane), UDM/UDR
(subscription data), AUSF (authentication), PCF (policy), CHF (charging), NSSF (slice
selection), NEF (exposure), NRF (discovery). Network slicing (S-NSSAI = SST + SD) and
management of slices are specified in TS 23.501 and the TS 28.53x management series
(TS 28.531 for provisioning).

## 3. Normative role mapping (correspondence rule CR-5)

| Platform service | 4G / EPC role | 5G / SBA function | Notes |
|---|---|---|---|
| `subscriber-service` | HSS (subscription data) | UDM + UDR | Permanent identifier masked; AUSF authentication stays with certified elements |
| `session-service` | P-GW-C / S-GW-C (session control) | SMF | Owns session state and slice binding; UPF forwarding is external |
| `policy-service` | PCRF | PCF | Decisions ALLOWED/DEGRADED/DENIED with rule and reason |
| `charging-service` | OCS (online charging) | CHF | Prepaid balance invariant; usage records as evidence |
| `network-slice-service` | — (no 4G equivalent) | NSSF + slice management (TS 28.531) | S-NSSAI registry; 5G-and-beyond capability |
| `exposure-service` | SCEF (limited) | NEF | Realizes GSMA Open Gateway / CAMARA APIs (QoD, SIM Swap) |
| `notification-service` | — | — (platform support) | Event-driven subscriber/ops notifications |

Functions: `usage-rating` (offline rating feeding CHF), `network-anomaly-scorer`
(assurance input), `sla-compliance-evaluator` (slice assurance evidence).

## 4. What is deliberately not here

AMF/MME mobility signaling, AUSF authentication vectors, N1/N2/N3/N4 protocol stacks, UPF
packet forwarding, and roaming interfaces (S6a, N32/SEPP) belong to certified network
elements. The platform integrates with them through the contracts in `contracts/` and
modeled events — the boundary is drawn on purpose and stated in the Architecture
Description (§8).
