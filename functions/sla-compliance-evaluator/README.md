# SLA Compliance Evaluator (serverless function)

Quarkus Funqy function on Knative. Compares measured slice KPIs (latency, availability)
with SLA targets and returns a verdict with one finding per breach.

Invoke: `POST /slaComplianceEvaluator` with a `SliceKpi`.
