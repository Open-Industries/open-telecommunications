# Service Level Objectives

Rolling 30 days, from gateway and service metrics.

| Service | Availability SLO | Latency SLO (p95) | Notes |
|---|---|---|---|
| API Gateway → any service | 99.9% | — | Error budget ~43 min/month |
| subscriber-service | 99.9% | reads < 300 ms | |
| session-service | 99.95% | establish < 800 ms | Includes synchronous policy evaluation |
| policy-service | 99.95% | evaluate < 200 ms | On the session critical path |
| charging-service | 99.95% | charge < 500 ms | Financial correctness path |
| network-slice-service | 99.9% | reads < 300 ms | Slice SLA targets live in slice data, evaluated by the SLA function |
| exposure-service | 99.9% | QoD create < 800 ms | CAMARA consumer-facing |
| notification-service | 99.5% | dispatch < 1 s | Provider latency excluded |
| Functions | 99.5% | cold start < 3 s | Scale-to-zero by design |

Per-slice-class network targets (latency budgets for URLLC-class slices, availability for
enterprise slices) are data in the Slice and Policy contexts and are judged by the
sla-compliance-evaluator — they are product commitments, not platform SLOs.
