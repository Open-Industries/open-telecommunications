# Deployment

| Path | Target |
|---|---|
| `kubernetes/` | Always-on microservices: `kubectl apply -k deploy/kubernetes/` |
| `knative/` | Serverless functions + Broker triggers |

Same manifests and images in every environment — central cloud or ETSI MEC edge —
only configuration changes (12-Factor V & X). See `docs/telecom/etsi-and-oran.md`.
