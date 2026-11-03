# Architecture Diagrams

Mermaid views governed by the viewpoints of `architecture-description.md` (ISO/IEC/IEEE 42010).

## D-1 — System Context (C4 Level 1)

```mermaid
flowchart TB
    SUB[Subscribers / devices] --> RAN[RAN<br/>gNB - eNB - O-RAN aligned]
    RAN --> CORE[Open Telecommunications<br/>Reference Telco Core Platform]
    RAN --> UPF[UPF - user plane<br/>network element, external]
    UPF --> DN[Data networks / internet]
    CORE --> BSS[BSS - CRM - order management<br/>TM Forum aligned, external]
    APPS[Application developers] -->|CAMARA / Open Gateway APIs| CORE
    CORE --> IDP[Identity provider<br/>OIDC / OAuth 2.1]
    CORE --> NOTIF[Notification providers<br/>email - SMS - push]
```

## D-2 — Containers (C4 Level 2)

```mermaid
flowchart LR
    GW[API Gateway] --> SUBS[subscriber-service :8081]
    GW --> EXP[exposure-service :8086]
    SES[session-service :8082] --> POL[policy-service :8083]
    SES --> CHG[charging-service :8084]
    SUBS --> SES
    POL --> SLI[network-slice-service :8085]
    SES -. usage .-> RATE[usage-rating - function]
    RATE -. rated .-> CHG
    SLI -. KPIs .-> SLA[sla-compliance-evaluator - function]
    KPI[RAN KPI events] -.-> ANOM[network-anomaly-scorer - function]
    CHG -. events .-> NT[notification-service :8087]
    SUBS -. events .-> NT
    SLI -. events .-> NT
    SUBS --> DB1[(db: subscribers)]
    SES --> DB2[(db: sessions)]
    POL --> DB3[(db: policies)]
    CHG --> DB4[(db: charging)]
    SLI --> DB5[(db: slices)]
    EXP --> DB6[(db: exposure)]
    NT --> DB7[(db: notifications)]
```

## D-3 — Deployment (cloud + MEC edge)

```mermaid
flowchart TB
    subgraph CENTRAL[Central cloud namespace]
        SVC[Services: Deployments + HPA, probes, limits]
        DBS[(PostgreSQL per service)]
        SVC --> DBS
    end
    subgraph EDGE[MEC edge site - optional, same images]
        FN[Functions: Knative, scale to zero<br/>anomaly scoring close to the RAN]
    end
    BR[Knative Broker - CloudEvents] --> SVC
    BR --> FN
    GIT[Git - this repository] --> CICD[CI/CD: build once, promote the same image]
    CICD --> CENTRAL
    CICD --> EDGE
```

## D-4 — Session establishment with policy and charging (sequence)

```mermaid
sequenceDiagram
    participant UE as Device / channel
    participant SES as session-service
    participant POL as policy-service
    participant CHG as charging-service
    UE->>SES: POST /sessions (subscriber, S-NSSAI, DNN, QoS class)
    SES->>POL: POST /policies/evaluate (slice type + QoS request)
    POL-->>SES: ALLOWED / DEGRADED / DENIED + reason
    SES-->>UE: 201 ESTABLISHED (synthetic IP allocated)
    SES-)CHG: usage events -> usage-rating -> charging (balance guarded)
```

## D-5 — Multi-generation alignment (4G → 5G → 6G framework)

```mermaid
flowchart LR
    subgraph G4[4G / EPC - TS 23.401]
        HSS[HSS] --- PCRF[PCRF] --- OCS[OCS]
    end
    subgraph G5[5G / SBA - TS 23.501]
        UDM[UDM] --- PCF[PCF] --- CHF[CHF]
    end
    subgraph GX[This platform - one codebase]
        SUB2[subscriber] --- POL2[policy] --- CHG2[charging]
    end
    G4 --> GX
    G5 --> GX
    GX -. IMT-2030 scenarios as config .-> G6[6G framework<br/>ITU-R M.2160: ISAC, AI-native, immersive]
```
