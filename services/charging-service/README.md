# Charging Service

Bounded context: **Convergent Charging** — prepaid balances and usage charging aligned
with the 5G **CHF** role (TS 23.501) and the 4G OCS. Core invariant, enforced in code and
tests: **a prepaid balance never goes negative** — over-drawing usage is rejected with
`422` and nothing is recorded. Money is integer minor units (ISO 4217).

| Item | Value |
|---|---|
| Port | `8084` (`HTTP_PORT`) |
| Base path | `/api/v1/charging/accounts` |
| Owns data | Charging accounts, usage records |
| Publishes | `charging.usage-charged`, `charging.balance-insufficient` |
