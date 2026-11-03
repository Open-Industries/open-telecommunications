# Standards Traceability Matrix

Every standard or framework claimed by this platform and where its evidence lives.
"Aligned to" throughout — no certification or regulatory-compliance claim is made.

| Standard / framework | Area | Evidence |
|---|---|---|
| ISO/IEC/IEEE 42010 | Architecture description | `docs/architecture/architecture-description.md`; `docs/adr/` |
| ISO/IEC 25010 | Quality model | `docs/architecture/quality-attributes.md` (QS-01…QS-10); tests; `docs/operations/slos.md` |
| ISO/IEC/IEEE 15288 / 12207 | Life-cycle processes | `docs/lifecycle/software-lifecycle.md` |
| ISO/IEC 27001 / 27002 | ISMS controls | `docs/security/` (architecture, control mapping, threat model) |
| Twelve-Factor App | Cloud methodology | `docs/architecture/twelve-factor.md` |
| 3GPP TS 23.401 | 4G/EPC architecture | `docs/telecom/3gpp-architecture.md` role mapping (HSS/PCRF/OCS) |
| 3GPP TS 23.501 / 23.502 | 5G SBA architecture & procedures | Same mapping (UDM/SMF/PCF/CHF/NSSF/NEF); session & policy services |
| 3GPP TS 28.531 | Network slice management | `network-slice-service`; `docs/telecom/3gpp-architecture.md` |
| ITU-R M.2160 (IMT-2030) | 6G framework & scenarios | `docs/telecom/6g-imt-2030.md` (framework alignment only — no 6G standard exists yet) |
| TM Forum ODA / Open APIs / eTOM | BSS/OSS alignment | `docs/telecom/tm-forum-oda.md` |
| ETSI NFV / MEC / ZSM | Virtualization, edge, automation | `docs/telecom/etsi-and-oran.md`; placement-free functions |
| O-RAN | RAN boundary posture | `docs/telecom/etsi-and-oran.md` (integration posture; no O-RAN interface claimed) |
| GSMA Open Gateway / CAMARA | Network exposure APIs | `services/exposure-service` (QoD, SIM Swap); `docs/api/api-standards.md` |
| OpenAPI 3.1 / AsyncAPI 2.6 / CloudEvents 1.0 | Contracts & events | `contracts/` |
| RFC 9457 | Error format | `Problem.java` in every service |
| ISO 4217 | Currency codes | Charging DTO validation; data architecture |
