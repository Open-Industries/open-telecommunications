# ETSI NFV / MEC / ZSM and O-RAN Alignment

## 1. ETSI NFV and MANO

The platform's deployables are cloud-native workloads (containers) rather than classic
VNFs; the ETSI NFV architectural framework still supplies the operating model adopters
use: MANO-style lifecycle (instantiate, scale, heal, terminate) realized here by
Kubernetes operators/HPA and Knative, with the same descriptors (`deploy/`) serving as
the deployment templates. Nothing in the domain code depends on the orchestrator.

## 2. ETSI MEC (Multi-access Edge Computing)

Latency-sensitive functions — first of all `network-anomaly-scorer` — are placement-free:
the same image runs centrally or at a MEC edge site close to the RAN (quality scenario
QS-06). Placement is a `deploy/` decision (namespace/node affinity at the edge cluster),
never a code change. This is the practical MEC lesson applied to an IT-plane platform.

## 3. ETSI ZSM (Zero-touch network and Service Management)

The assurance loop implemented by the functions — KPI scoring with auditable rationale,
SLA verdicts with findings, event-driven notification — is a deliberately small,
inspectable step on the ZSM path: closed loops whose every automated decision carries
its reason, so autonomy can grow without losing auditability (ISO 27002 A.8.15).

## 4. O-RAN

The RAN is an external system in the context view. The platform's integration posture is
O-RAN-friendly by construction: RAN KPIs arrive as CloudEvents (the anomaly scorer's
input), and slice/QoS intents leave through the policy and exposure contracts — the same
boundaries an O-RAN SMO/RIC integration uses. No O-RAN interface (E2, O1, A1) is
implemented or claimed here.
