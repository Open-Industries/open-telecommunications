# TM Forum Alignment — ODA, Open APIs and eTOM

The platform's BSS/OSS-facing alignment with TM Forum frameworks. Alignment, not
certification: TM Forum conformance is a separate program an adopter may pursue.

## 1. Open Digital Architecture (ODA)

The platform's services correspond to ODA *core commerce* and *production* components
realized as independently deployable software units with published Open APIs — the same
shape ODA prescribes (decoupled components, API-first, cloud-native runtime, which the
Twelve-Factor mapping in `../architecture/twelve-factor.md` supplies).

## 2. Open API mapping (indicative)

| Platform surface | TM Forum Open API alignment |
|---|---|
| Subscriber onboarding/profile | TMF629 Customer Management (party/customer side), TMF632 Party Management |
| Product/plan and tariff catalogue | TMF620 Product Catalog Management |
| Charging accounts and usage | TMF635 Usage Management, TMF654 Prepay Balance Management, TMF678 Customer Bill Management (downstream) |
| Service/session activation | TMF640 Service Activation and Configuration |
| Slice as a service resource | TMF638 Service Inventory, TMF641 Service Ordering |

CAMARA APIs (exposure context) are a GSMA/Linux Foundation family, complementary to TM
Forum APIs: CAMARA faces application developers; TM Forum APIs face the BSS/OSS stack.

## 3. eTOM process alignment (Level-2, indicative)

- **Fulfillment**: subscriber onboarding → session establishment → slice activation.
- **Assurance**: KPI anomaly scoring → SLA compliance evaluation → notification.
- **Billing**: usage rating → charging records → (adopter's bill generation downstream).

The full process decomposition belongs to each adopter's eTOM tailoring; the events in
`contracts/asyncapi.yaml` are the integration points those processes consume.
