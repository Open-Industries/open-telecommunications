# Network Anomaly Scorer (serverless function)

Quarkus Funqy function on Knative. Scores RAN KPI events with transparent rules and
returns `NORMAL | WARNING | CRITICAL` plus the rationale — a ZSM closed-loop building block.

Invoke: `POST /networkAnomalyScorer` with a `NetworkKpiEvent`.
