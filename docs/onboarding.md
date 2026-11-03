# Developer Onboarding

1. Install Java 21 and Maven 3.9+.
2. `mvn verify` — the full suite must pass on a clean machine (QS-09).
3. Optional backing services: `docker compose up -d`.
4. Run the pilot: `mvn -pl services/subscriber-service quarkus:dev` → `/docs`, `/q/dev-ui`.
5. Read: `README.md` → `docs/architecture/architecture-description.md` →
   `docs/telecom/3gpp-architecture.md` → `docs/api/api-standards.md`.
6. Before changing a context, read its ADRs (`docs/adr/`).
