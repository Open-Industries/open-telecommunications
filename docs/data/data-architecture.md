# Data Architecture

Data view (VP-6) of the Architecture Description.

## 1. Ownership

| Data | Owner (only writer) | Store (reference → production) |
|---|---|---|
| Subscriber profiles | subscriber-service | in-memory → PostgreSQL `subscribers` |
| PDU sessions | session-service | in-memory → PostgreSQL `sessions` |
| Policy rules | policy-service | in-memory → PostgreSQL `policies` |
| Charging accounts & usage records | charging-service | in-memory → PostgreSQL `charging` |
| Network slices | network-slice-service | in-memory → PostgreSQL `slices` |
| QoD sessions | exposure-service | in-memory → PostgreSQL `exposure` |
| Notifications trail | notification-service | in-memory → PostgreSQL `notifications` |

In-memory adapters exist so the platform runs anywhere with zero setup; production
persistence is configuration (`DB_KIND`, `DB_URL`) plus the JPA adapter — the domain and
contracts never change (12-Factor IV).

## 2. Identifiers and privacy

- Permanent identifiers (IMSI/SUPI): validated, **masked at creation, never stored or
  returned in clear**. The platform's internal key is a UUID; the MSISDN is the public
  identifier. All values in this repository are synthetic.
- 5G privacy concepts (SUCI concealment on the air interface) belong to network elements;
  this platform's duty is to never undo that protection in IT systems.

## 3. Money, tariffs and usage

Amounts are integer minor units + ISO 4217 currency. The **reference tariff catalogue is
single and versioned** (`reference-tariff-v1`) and is applied identically by the Charging
Service and the usage-rating function (CR-6). Usage records are append-only financial
evidence; corrections are compensating records, never edits.

## 4. Consistency and retention

Strong within a context; eventual across contexts via CloudEvents with idempotent
consumers. Retention: usage/charging records per the adopter's regulatory schedule;
subscriber PII erasable per data-subject rights while charging evidence is retained in
pseudonymized form (account UUIDs only). Backups per service, encrypted, restore tested
(ISO 27002 A.8.13).
