# Usage Rating (serverless function)

Quarkus Funqy function on Knative (scale to zero). Rates raw usage events into charging
units with the reference tariff catalogue shared with the Charging Service.

Invoke: `POST /usageRating` with a `UsageEvent`.
