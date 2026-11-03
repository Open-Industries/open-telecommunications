# Notification Service

Bounded context: **Notifications**. Channel-agnostic dispatch (EMAIL/SMS/PUSH) driven by
domain events; providers are outbound adapters selected by configuration.

| Item | Value |
|---|---|
| Port | `8087` (`HTTP_PORT`) |
| Base path | `/api/v1/notifications` |
| Owns data | Notification requests and delivery trail |
| Consumes | `charging.usage-charged`, `slice.activated`, `subscriber.onboarded`, … |
