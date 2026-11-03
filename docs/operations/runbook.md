# Operations Runbook

## Deploy (per environment, incl. edge)

1. Confirm release tag and image digests in the release record.
2. `kubectl apply -k deploy/kubernetes/overlays/<env>`; functions: `kubectl apply -f deploy/knative/`.
3. Verify: Deployments ready, `/q/health/ready` green via the gateway, SLO dashboard nominal.
4. Rollback: re-apply the previous overlay (images are immutable, promoted, never rebuilt).

## Health endpoints (every service)

Liveness `GET /q/health/live` · Readiness `GET /q/health/ready` · Metrics `GET /q/metrics` ·
API docs `/docs` · Contract `/openapi`.

## Common incidents

| Symptom | First checks | Action |
|---|---|---|
| Sessions failing to establish | Policy-service decisions (DENIED reasons); slice status | Fix the policy rule or activate the slice; decisions carry their reason |
| Charging rejects with 422 | Account balance vs tariff | Expected behavior for over-draw; top-up or correct the usage source — never edit balances by hand |
| Anomaly scorer silent | Broker delivery metrics; KPI event types vs `contracts/asyncapi.yaml` | Restore the event feed; the scorer is stateless |
| SLA verdicts all breaching | Slice targets vs measured KPIs | Check for a genuine network degradation before touching targets |

## Admin processes (12-Factor XII)

Tariff loads, migrations, SLA re-evaluations and idempotency-key purges run as one-off
Jobs or function invocations with the same image and configuration — never as interactive
sessions inside production pods.
