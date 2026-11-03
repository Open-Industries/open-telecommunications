# Architecture Description — Open Telecommunications Reference Telco Core Platform

**Conformance**: this document is the Architecture Description (AD) of the platform,
structured according to **ISO/IEC/IEEE 42010**. It identifies the system, its stakeholders
and concerns, the viewpoints framing those concerns, the views they govern, the decisions
and rationale, and the correspondences keeping the views consistent.

| Field | Value |
|---|---|
| System of interest | Open Telecommunications — Reference Telco Core Platform (`Open-Industries/open-telecommunications`) |
| Version of this AD | 1.0 |
| Status | Baseline |
| Architecture framework | ISO/IEC/IEEE 42010; views with C4 conventions and Mermaid |
| Telecom frameworks | 3GPP TS 23.401 (4G/EPC), TS 23.501/23.502 (5G SBA), TS 28.531 (slicing); ITU-R IMT-2030 framework (M.2160); TM Forum ODA; ETSI NFV/MEC/ZSM; GSMA Open Gateway/CAMARA |
| Implementation stack | Java 21, Quarkus 3, Maven multi-module; Kubernetes/OpenShift; Knative (OpenShift Serverless) |

---

## 1. System overview and purpose

A **generic telco core platform**: the subscriber, session, policy, charging, slicing and
exposure capabilities every connectivity provider needs, designed to **deploy as-is for a
reference environment and adapt by configuration** (tariffs, slices, QoS profiles,
providers) rather than by forking code. The platform is the IT-plane realization of the
3GPP control-plane roles; radio and user-plane network elements (RAN, UPF) remain network
equipment outside this system boundary, integrated through modeled interfaces.

Architectural style: **microservices** (one deployable per bounded context) plus
**serverless functions** for event-driven work, aligned with **The Twelve-Factor App**.

## 2. Stakeholders and concerns (42010 §5.3)

| Stakeholder | Role | Key concerns |
|---|---|---|
| CSP / MNO adopting the platform | Owns and operates it | Multi-generation support (4G/5G, 6G readiness), cost, time-to-market |
| MVNO / private-network owner | Runs a lighter footprint | Adaptability, footprint, slicing self-service |
| Network engineering | Owns RAN/core integration | Correct role alignment with 3GPP, slice and QoS semantics |
| BSS/OSS teams | Billing, CRM, assurance | TM Forum alignment, charging integrity, SLA evidence |
| Product / commercial | Tariffs and offers | Tariff configurability, CAMARA exposure revenue |
| Platform / SRE | Runs Kubernetes/OpenShift incl. edge | Reliability, observability, edge placement, zero-touch automation |
| CISO / privacy officer | ISMS (ISO 27001) + telecom privacy | Permanent-identifier protection, lawful-intercept boundary clarity, audit |
| Regulators / auditors | Oversight | Traceability, data retention, emergency-services and LI handled by certified elements (out of scope, see §8) |
| Application developers (CAMARA consumers) | Build on network APIs | Contract stability, predictable errors, sandboxable synthetic data |
| Subscribers | Indirect users | Correct charging, session quality, privacy of identifiers and usage data |

## 3. Architecture viewpoints (42010 §5.4)

| Viewpoint | Frames concerns of | Model kinds / conventions |
|---|---|---|
| **VP-1 Context** | Adopters, regulators | C4 System Context; RAN/UPF and BSS as external systems |
| **VP-2 Container** | Architects, developers, SRE | C4 Container; one container = one deployable; sync REST vs async CloudEvents |
| **VP-3 Component** | Developers | Hexagonal (ports & adapters) per service |
| **VP-4 Domain** | Product, network engineering | Bounded-context map aligned to 3GPP roles (`domain-model.md`) |
| **VP-5 Network alignment** | Network engineering | 4G EPC → 5G SBA role/interface mapping (`../telecom/3gpp-architecture.md`) |
| **VP-6 Data** | Architects, privacy officer | Database-per-service; identifier handling (`../data/data-architecture.md`) |
| **VP-7 Deployment** | SRE | Cloud + MEC edge topology, environments |
| **VP-8 Security** | CISO, auditors | Trust boundaries, OIDC, ISO 27002 mapping (`../security/`) |
| **VP-9 Quality & operations** | SRE, BSS/assurance | ISO 25010 model, per-slice SLOs (`quality-attributes.md`, `../operations/slos.md`) |

## 4. Architecture views

### 4.1 Context view (VP-1)

See `diagrams.md` D-1. Externals: RAN (O-RAN-aligned), the UPF user plane, the operator's
BSS/CRM stack, CAMARA API consumers, notification providers, and the IdP.

### 4.2 Container view (VP-2)

See `diagrams.md` D-2 and the README diagram. Seven Quarkus microservices aligned to 3GPP
roles (UDM/HSS, SMF, PCF/PCRF, CHF/OCS, NSSF/slice management, NEF, notifications) and
three Funqy functions (usage rating, anomaly scoring, SLA evaluation) on Knative, fronted
by an API Gateway, with one database per service and a CloudEvents backbone.

### 4.3 Component view (VP-3)

Identical hexagonal layout in every service (REST adapter → application service → domain
→ repository port), the same rule as the Open Industries reference cores: learn one
service, and you have learned all of them.

### 4.4 Domain view (VP-4)

Full detail in `domain-model.md`. Contexts and rules: Subscriber (identifiers masked),
Session (PDU lifecycle bound to S-NSSAI), Policy (decisions with reasons), Charging
(balance never negative), Slice (S-NSSAI registry and lifecycle), Exposure (CAMARA-style
contracts), Notification (delivery trail).

### 4.5 Network alignment view (VP-5)

`../telecom/3gpp-architecture.md`: EPC roles (HSS, PCRF, OCS, P-GW-C) and 5G SBA functions
(UDM, SMF, PCF, CHF, NSSF, NEF) mapped one-to-one onto platform services; 6G readiness
treated per the IMT-2030 framework in `../telecom/6g-imt-2030.md`.

### 4.6 Data view (VP-6)

`../data/data-architecture.md`: database-per-service; usage records as the
charging evidence trail; permanent identifiers masked at rest and in transit through the
platform; retention per adopter regulation.

### 4.7 Deployment view (VP-7)

`diagrams.md` D-3 and `deploy/`: identical topology per environment; latency-sensitive
functions may be placed at MEC edge sites by configuration only.

### 4.8 Security view (VP-8)

`../security/security-architecture.md` and the ISO 27002 mapping: OIDC at the edge, mTLS
inside, per-service least-privilege credentials, secrets only from the platform store,
audit logging of every state change. Telecom-specific threats (signaling abuse, SIM-swap
fraud) are in the threat model.

### 4.9 Quality & operations view (VP-9)

ISO 25010 model and scenarios in `quality-attributes.md`; per-slice-class SLOs in
`../operations/slos.md`; the anomaly scorer and SLA evaluator are the platform's own
assurance feedback loop (a ZSM building block).

## 5. Architecture decisions (42010 §5.6)

Recorded as ADRs in `docs/adr/`: ADR-0001 microservices + serverless; ADR-0002 Java/Quarkus;
ADR-0003 database per service; ADR-0004 charging integrity (balance never negative);
ADR-0005 permanent identifiers masked, synthetic data only; ADR-0006 3GPP role alignment
(4G↔5G) as the domain organizing principle; ADR-0007 CAMARA-style exposure contracts;
ADR-0008 API-first + RFC 9457 + CloudEvents; ADR-0009 mandatory idempotency; ADR-0010
security model (OIDC/mTLS/platform secrets); ADR-0011 Twelve-Factor binding;
ADR-0012 6G readiness by framework alignment (IMT-2030), not by speculation.

## 6. Correspondences and consistency rules (42010 §5.7)

- **CR-1** Every container maps to exactly one Maven module and one manifest set in `deploy/`.
- **CR-2** Every public operation appears in `contracts/` (OpenAPI) or is generated from
  code and verified in CI; drift fails the build.
- **CR-3** Every event named in any view appears in `contracts/asyncapi.yaml` (CloudEvents).
- **CR-4** Each context owns exactly one database schema; cross-context database access
  is an architecture violation.
- **CR-5** Every 3GPP role named in a view appears in the mapping table of
  `../telecom/3gpp-architecture.md` with its 4G and 5G names.
- **CR-6** Every tariff value used by the Charging Service also appears in the usage-rating
  function's catalogue (single reference tariff, versioned as `reference-tariff-v1`).

## 7. Rationale (summary)

The 5G SBA already thinks in services with published interfaces — bounded-context
microservices are its natural IT realization, and the same contexts absorb the 4G roles
(HSS→UDM, PCRF→PCF, OCS→CHF), giving one codebase for migration-era networks. Serverless
functions fit bursty assurance and rating work at zero idle cost. Quarkus gives one stack
for services and functions. 6G is approached honestly: IMT-2030 scenarios (notably ISAC
and AI-native operation) are represented as configuration-level extension points, because
no finished 6G standard exists yet.

## 8. Trade-offs, scope boundaries and open issues

- **Not network elements**: this platform does not implement RAN, UPF forwarding, or
  certified 3GPP NF protocol stacks (N1/N2/N4 signaling); it models their IT-plane roles
  and contracts. Lawful intercept, emergency-call routing and number portability are
  certified-element/regulatory functions, out of scope by design.
- Eventual consistency between session events and charging is accepted and made explicit;
  charging records are the financial evidence of record.
- Reference persistence is in-memory for zero-setup evaluation; production adopters
  select the JPA adapter by configuration.
